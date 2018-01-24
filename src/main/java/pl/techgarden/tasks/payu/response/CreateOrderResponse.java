package pl.techgarden.tasks.payu.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@ToString
public class CreateOrderResponse {
    private String redirectUri;
    private String orderId;
    private HttpStatus status;
    private String extOrderId;
}
