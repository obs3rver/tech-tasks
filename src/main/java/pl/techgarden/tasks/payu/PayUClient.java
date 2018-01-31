package pl.techgarden.tasks.payu;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.techgarden.tasks.payu.FeignRedirectionErrorDecoder.FeignRedirectionException;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import static java.util.stream.Collectors.joining;

@Slf4j
@Component
public class PayUClient {
    private final Validator validator;
    private final Decoder decoder;
    private final PayuCreateOrderClient payuCreateOrderClient;

    public PayUClient(
            @NonNull Validator validator,
            @NonNull Decoder decoder,
            @NonNull PayuCreateOrderClient payuCreateOrderClient
    ) {
        this.validator = validator;
        this.decoder = decoder;
        this.payuCreateOrderClient = payuCreateOrderClient;
    }

    public Optional<CreateOrderResponse> createOrder(final CreateOrderRequest request) {
        validateRequestArgument(request);

        try {
            log.debug("Sending {}", request);
            val responseEntity = payuCreateOrderClient.createOrder(request);
            return Optional.ofNullable(responseEntity.getBody());
        }
        catch (FeignException fe) {
            log.error("FeignException: {}", fe.getMessage());
            return Optional.empty();
        }
        catch (FeignRedirectionException re) {
            log.error("FeignRedirectionException: {}", re.getMessage());
            return decodeResponse(re.response());
        }

    }

    private void validateRequestArgument(CreateOrderRequest request) {
        val requestViolations = validator.validate(request);

        if (thereAre(requestViolations)) {
            throw new InvalidRequestArgumentException(withMessageFrom(requestViolations));
        }
    }

    private Optional<CreateOrderResponse> decodeResponse(final Response response) {
        try {
            CreateOrderResponse responseObject = (CreateOrderResponse)
                    decoder.decode(response, CreateOrderResponse.class);
            return Optional.ofNullable(responseObject);
        }
        catch (IOException ioe) {
            log.error("IOException: {}", ioe.getMessage());
            return Optional.empty();
        }
        catch (FeignException fe) {
            log.error("FeignException: {}", fe.getMessage());
            return Optional.empty();
        }
    }

    private boolean thereAre(final Set<ConstraintViolation<CreateOrderRequest>> violations) {
        return !violations.isEmpty();
    }

    private String withMessageFrom(final Set<ConstraintViolation<CreateOrderRequest>> violations) {
        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(joining("\n"));
    }

}
