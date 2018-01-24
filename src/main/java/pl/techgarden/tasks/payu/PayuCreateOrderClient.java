package pl.techgarden.tasks.payu;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import pl.techgarden.tasks.payu.request.CreateOrderRequest;
import pl.techgarden.tasks.payu.response.CreateOrderResponse;

@FeignClient(
        value = "createOrder",
        url = "${payu.createOrder.url}"
)
interface PayuCreateOrderClient {
    @PostMapping(headers = {
            "Content-Type: application/json",
            "Accept: application/json"
    })
    CreateOrderResponse createOrder(CreateOrderRequest request);
}
