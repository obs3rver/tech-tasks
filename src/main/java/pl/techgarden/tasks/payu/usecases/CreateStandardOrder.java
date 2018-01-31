package pl.techgarden.tasks.payu.usecases;

import org.springframework.stereotype.Component;
import pl.techgarden.tasks.payu.CreateOrderRequest;
import pl.techgarden.tasks.payu.PayUClient;

@Component
public class CreateStandardOrder extends CreateBaseOrder {

    public CreateStandardOrder(PayUClient payUClient) {
        super(payUClient);
    }

    @Override
    public BaseOrderRequestBuilder requestBuilder() {
        return new CreateStandardOrderRequestBuilder();
    }

    public class CreateStandardOrderRequestBuilder extends BaseOrderRequestBuilder {

        @Override
        CreateOrderRequest buildRequest() {
            return CreateOrderRequest.builder()
                    .customerIp(customerIp)
                    .merchantPosId(merchantPosId)
                    .description(description)
                    .currencyCode(currencyCode)
                    .products(products)
                    .totalAmount(calculateTotalAmount(products))
                    .build();
        }

    }
}
