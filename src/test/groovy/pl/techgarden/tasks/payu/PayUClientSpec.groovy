package pl.techgarden.tasks.payu

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class PayUClientSpec extends Specification implements PayuData {

    @Autowired
    private PayUClient payUClient

    def "should not send CreateOrderRequest and throw InvalidRequestArgumentException instead"() {
        given:
        CreateOrderRequest request = aPartiallyInvalidCreateOrderRequestWithoutCustomerIp()

        when:
        payUClient.createOrder(request)

        then:
        thrown(InvalidRequestArgumentException)
    }

    def "should send CreateOrderRequest to PayU"() {
        given:
        CreateOrderRequest request = aValidCreateOrderRequest()

        when:
        def responseOpt = payUClient.createOrder(request)

        then:
        responseOpt.isPresent()
        responseOpt.get().status.isOk()
    }

}