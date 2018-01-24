package pl.techgarden.tasks.payu;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.techgarden.tasks.payu.request.CreateOrderRequest;
import pl.techgarden.tasks.payu.response.CreateOrderResponse;

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

    public CreateOrderResponse createOrder(final CreateOrderRequest request) {
        val violations = validator.validate(request);

        if (thereAreNo(violations)) {
            log.debug("Sending {}", request);
            return payuCreateOrderClient.createOrder(request);
        }

        throw new InvalidRequestArgumentException(withMessageFrom(violations));
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
