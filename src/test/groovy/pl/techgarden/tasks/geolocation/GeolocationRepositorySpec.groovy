package pl.techgarden.tasks.geolocation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class GeolocationRepositorySpec extends Specification implements GeolocationData {

    @Autowired
    GeolocationRepository geolocationRepository

    def "should store location data in repository"() {
        given:
        Geolocation location = aSampleLocation()

        when:
        location = geolocationRepository.insert(location)
        Geolocation locationFound = geolocationRepository.findOne(location.id)

        then:
        location.id != null
        locationFound == location
    }

}
