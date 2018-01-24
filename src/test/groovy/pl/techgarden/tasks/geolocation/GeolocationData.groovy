package pl.techgarden.tasks.geolocation

import org.springframework.data.mongodb.core.geo.GeoJsonPoint

trait GeolocationData {
    static Geolocation aSampleLocation() {
        Geolocation.builder()
                .subject("sample location")
                .location(new GeoJsonPoint(0.0, 0.0))
                .build()
    }

    static Geolocation.Dto aSampleLocationDto() {
        aSampleLocation().toDto()
    }

    static Geolocation.Dto anInvalidSampleLocationDto() {
        Geolocation.Dto.builder().build()
    }
}