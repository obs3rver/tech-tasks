package pl.techgarden.tasks.payu;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
        value = "createOrder",
        url = "${payu.createOrder.url}",
        configuration = {PayuClientConfiguration.class}
)
interface PayuCreateOrderClient {

    @PostMapping(consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    ResponseEntity<CreateOrderResponse> createOrder(CreateOrderRequest request);

}
