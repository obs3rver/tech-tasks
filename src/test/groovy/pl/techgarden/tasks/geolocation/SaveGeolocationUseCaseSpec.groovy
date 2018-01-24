package pl.techgarden.tasks.geolocation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SaveGeolocationUseCaseSpec extends Specification implements GeolocationData {

    @Autowired
    SaveGeolocationUseCase saveGeolocationUseCase

    @Autowired
    GeolocationRepository geolocationRepository

    def "should store location data in repository"() {
        given:
        Geolocation.GeolocationDto locationDto = aSampleLocationDto()

        when:
        Geolocation location = saveGeolocationUseCase.saveGeolocation(locationDto)
        Geolocation locationFound = geolocationRepository.findOne(location.id)

        then:
        location.id != null
        locationFound == location
    }

}
