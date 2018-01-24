package pl.techgarden.tasks.payu.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@ToString
public class CreateOrderRequest {
    @NotBlank
    private String customerIp;

    @Min(1)
    private long merchantPosId;

    @NotBlank
    private String description;

    @NotNull
    private CurrencyCode currencyCode;

    @Min(1)
    private long totalAmount;

    @NotNull
    @Size(min = 1)
    private List<Product> products;

    @Getter
    @Builder
    @ToString
    public static class Product {
        @NotBlank
        private String name;

        @Min(1)
        private long unitPrice;

        @Min(1)
        private long quantity;
    }

    public enum CurrencyCode {
        PLN(1),
        EUR(1),
        HUF(100);

        private final int scaling;

        CurrencyCode(int scaling) {
            this.scaling = scaling;
        }

        public long scaleAmount(long amount) {
            return amount * scaling;
        }
    }
}
