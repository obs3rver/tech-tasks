package pl.techgarden.tasks.geolocation;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "locations")
@Value
@Wither
@Builder
public class Geolocation {
    @Id
    String id;
    String subject;
    GeoJsonPoint location;

    static Geolocation fromDto(final Dto dto) {
        return Geolocation.builder()
                .subject(dto.subject)
                .location(
                        new GeoJsonPoint(
                                dto.longitude,
                                dto.latitude
                        ))
                .build();
    }

    Dto toDto() {
        return Dto.builder()
                .longitude(location.getX())
                .latitude(location.getY())
                .subject(subject)
                .build();
    }

    @Value
    @Builder
    public static class Dto {
        @NotNull
        Double longitude;

        @NotNull
        Double latitude;

        @NotBlank
        String subject;
    }
}
