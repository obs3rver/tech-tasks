package pl.techgarden.tasks.geolocation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.techgarden.tasks.geolocation.Geolocation.GeolocationDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveGeolocationUseCase {
    private final GeolocationRepository repository;

    public Geolocation saveGeolocation(final GeolocationDto dto) {
        final Geolocation geolocation = Geolocation.fromDto(dto);
        log.debug("Inserting geolocation to repository: {}", geolocation);
        return repository.insert(geolocation);
    }
}
