package pl.techgarden.tasks.payu;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CreateOrderResponse {
    private String orderId;
    private String extOrderId;
    private String redirectUri;
    private Status status;

    public static class Status {
        static final String SUCCESS = "SUCCESS";

        String statusCode;

        boolean isOk() {
            return statusCode.equals(SUCCESS);
        }
    }
}
