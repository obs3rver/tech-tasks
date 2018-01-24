package pl.techgarden.tasks.payu.request

import pl.techgarden.tasks.payu.PayuData
import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class CreateOrderRequestSpec extends Specification implements PayuData {
    @Shared
    private Validator validator

    def setupSpec() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory()
        this.validator = vf.getValidator()
    }

    def "should validate invalid CreateOrderRequest object"() {
        given:
        CreateOrderRequest invalidRequest = anInValidCreateOrderRequest()

        when:
        def violations = validator.validate(invalidRequest)

        then:
        violations.size() == 6
    }

    def "should validate invalid Product object"() {
        given:
        CreateOrderRequest.Product invalidProduct = anInvalidProduct()

        when:
        def violations = validator.validate(invalidProduct)

        then:
        violations.size() == 3
    }
}
