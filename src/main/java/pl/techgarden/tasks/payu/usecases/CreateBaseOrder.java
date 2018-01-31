package pl.techgarden.tasks.payu.usecases;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.techgarden.tasks.payu.CreateOrderRequest;
import pl.techgarden.tasks.payu.CreateOrderResponse;
import pl.techgarden.tasks.payu.PayUClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notEmpty;
import static org.springframework.util.Assert.notNull;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
abstract class CreateBaseOrder implements Command {
    private final PayUClient payUClient;

    Optional<CreateOrderResponse> execute(final CreateOrderRequest createOrderRequest) {
        return payUClient.createOrder(createOrderRequest);
    }

    public abstract BaseOrderRequestBuilder requestBuilder();

    abstract class BaseOrderRequestBuilder {
        protected String customerIp;
        protected long merchantPosId;
        protected String description;
        protected CreateOrderRequest.CurrencyCode currencyCode;
        protected long totalAmount;
        protected List<CreateOrderRequest.Product> products;

        public BaseOrderRequestBuilder customerIp(String customerIp) {
            this.customerIp = customerIp;
            return this;
        }

        public BaseOrderRequestBuilder merchantPosId(long merchantPosId) {
            this.merchantPosId = merchantPosId;
            return this;
        }

        public BaseOrderRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BaseOrderRequestBuilder currencyCode(CreateOrderRequest.CurrencyCode currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        public BaseOrderRequestBuilder products(CreateOrderRequest.Product... products) {
            this.products = Collections.unmodifiableList(asList(products));
            return this;
        }

        long calculateTotalAmount(List<CreateOrderRequest.Product> products) {
            return totalAmount = products.stream()
                    .mapToLong(it -> it.getQuantity() * it.getUnitPrice())
                    .sum();
        }

        public Optional<CreateOrderResponse> buildAndExecute() {
            final CreateOrderRequest request = buildRequest();
            validate();
            return execute(request);
        }

        void validate() {
            notNull(customerIp, "customerIp is null");
            isTrue(merchantPosId > 0, "merchantPosId is zero");
            notNull(description, "description is null");
            notNull(currencyCode, "currencyCode is null");
            notEmpty(products, "products collection is empty");
            isTrue(totalAmount > 0, "totalAmount is zero");
        }

        abstract CreateOrderRequest buildRequest();
    }

}
