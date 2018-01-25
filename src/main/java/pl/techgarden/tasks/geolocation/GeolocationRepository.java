package pl.techgarden.tasks.geolocation;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface GeolocationRepository extends MongoRepository<Geolocation, String> {

}
