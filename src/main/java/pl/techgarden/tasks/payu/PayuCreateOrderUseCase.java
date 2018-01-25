package pl.techgarden.tasks.payu;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import static java.util.stream.Collectors.joining;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayuCreateOrderUseCase {
    private final PayuCreateOrderClient payuCreateOrderClient;
    private final Validator validator;

    public Optional<CreateOrderResponse> createOrder(final CreateOrderRequest request) {
        val requestViolations = validator.validate(request);

        if (thereAreNo(requestViolations)) {
            CreateOrderResponse response = null;
            
            try {
                log.debug("Sending {}", request);
                response = payuCreateOrderClient.createOrder(request);
            } catch (FeignException fe) {
                log.error(fe.getMessage());
            }

            return Optional.ofNullable(response);
        }

        throw new InvalidRequestArgumentException(withMessageFrom(requestViolations));
    }

    private boolean thereAreNo(final Set<ConstraintViolation<CreateOrderRequest>> violations) {
        return violations.isEmpty();
    }

    private String withMessageFrom(final Set<ConstraintViolation<CreateOrderRequest>> violations) {
        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(joining("\n"));
    }

}
