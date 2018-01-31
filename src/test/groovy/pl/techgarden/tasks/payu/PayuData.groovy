package pl.techgarden.tasks.payu

import pl.techgarden.tasks.payu.CreateOrderRequest.Product

import static java.util.Arrays.asList
import static java.util.Collections.unmodifiableList

trait PayuData {
    static CreateOrderRequest anInValidCreateOrderRequest() {
        CreateOrderRequest.builder().build()
    }

    static CreateOrderRequest aPartiallyInvalidCreateOrderRequestWithoutCustomerIp() {
        CreateOrderRequest.builder()
                .merchantPosId(145227)
                .description("RTV market")
                .currencyCode(CreateOrderRequest.CurrencyCode.PLN)
                .totalAmount(21000)
                .products(
                unmodifiableList(asList(
                        aMouseProduct(),
                        aHdmiCableProduct()
                )))
                .build()
    }

    static Product anInvalidProduct() {
        Product.builder().build()
    }

    static CreateOrderRequest aValidCreateOrderRequest() {
        CreateOrderRequest.builder()
                .customerIp("127.0.0.1")
                .merchantPosId(145227)
                .description("RTV market")
                .currencyCode(CreateOrderRequest.CurrencyCode.PLN)
                .totalAmount(21000)
                .products(
                unmodifiableList(asList(
                        aMouseProduct(),
                        aHdmiCableProduct()
                )))
                .build()
    }

    static Product aHdmiCableProduct() {
        Product.builder()
                .name("HDMI cable")
                .unitPrice(6000)
                .quantity(1)
                .build()
    }

    static Product aMouseProduct() {
        Product.builder()
                .name("Wireless mouse")
                .unitPrice(15000)
                .quantity(1)
                .build()
    }
}