package pl.techgarden.tasks.geolocation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locations")
@Data
@Setter(AccessLevel.NONE)
@Builder
public class Geolocation {
    @Id
    private String id;
    private String subject;
    private GeoJsonPoint location;

    static Geolocation fromDto(final GeolocationDto dto) {
        return Geolocation.builder()
                .subject(dto.subject)
                .location(
                        new GeoJsonPoint(
                                Double.valueOf(dto.longitude),
                                Double.valueOf(dto.latitude)
                        ))
                .build();
    }

    GeolocationDto toDto() {
        return new GeolocationDto(
                String.valueOf(location.getX()),
                String.valueOf(location.getY()),
                subject
        );
    }

    @NoArgsConstructor
    @AllArgsConstructor
    static class GeolocationDto {
        String longitude;
        String latitude;
        String subject;
    }
}
