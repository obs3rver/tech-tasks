package pl.techgarden.tasks.geolocation

import org.junit.After
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = MOCK)
class GeolocationControllerSpec extends Specification implements GeolocationData, JsonUtils {
    static final String ENDPOINT = "/api/locations"

    @Autowired
    WebApplicationContext webApplicationContext

    MockMvc mockMvc

    @Autowired
    GeolocationRepository geolocationRepository

    @Before
    void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    @After
    void cleanRepos() {
        geolocationRepository.deleteAll()
    }

    def "should post location data"() {
        given: 'A sample geolocation dto'
        Geolocation.Dto locationDto = aSampleLocationDto()

        when: 'post to locations endpoint'
        ResultActions postGeolocation = mockMvc.perform(
                post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(locationDto))
        )

        then: 'response is created 201'
        postGeolocation.andExpect(status().isCreated())

        and: 'geolocation object was saved in repository'
        def foundGeolocationOpt = geolocationRepository.findAll().stream().findFirst()
        foundGeolocationOpt.isPresent()
        foundGeolocationOpt.get().toDto() == locationDto
    }

    def "should not post location with incomplete data"() {
        given: 'An incomplete geolocation dto'
        Geolocation.Dto incompleteLocationDto = anIncompleteSampleLocationDto()

        when: 'post to locations endpoint'
        ResultActions postGeolocation = mockMvc.perform(
                post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incompleteLocationDto))
        )

        then: 'response is 400'
        postGeolocation.andExpect(status().isBadRequest())
                .andExpect(content().json(expectedErrorMsgForIncompleteData()))

        and: 'geolocation object was not saved in repository'
        geolocationRepository.findAll().isEmpty()
    }

    def "should not post location with invalid data"() {
        given: 'An invalid geolocation dto'
        String invalidRequestData = "{\"latitude\": \"abc\"}"

        when: 'post to locations endpoint'
        ResultActions postGeolocation = mockMvc.perform(
                post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestData)
        )

        then: 'response is 400'
        postGeolocation.andExpect(status().isBadRequest())
                .andExpect(content().json(expectedErrorMsgForInvalidData()))

        and: 'geolocation object was not saved in repository'
        geolocationRepository.findAll().isEmpty()
    }

    String expectedErrorMsgForIncompleteData() {
        asJsonString(GeolocationController.ErrorResponse.of([
                "latitude may not be null",
                "longitude may not be null",
                "subject may not be empty"
        ]))
    }

    String expectedErrorMsgForInvalidData() {
        asJsonString(GeolocationController.ErrorResponse.of(["Invalid input data"]))
    }
}
