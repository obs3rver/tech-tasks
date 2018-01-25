package pl.techgarden.tasks.payu

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Ignore
import spock.lang.Specification

@SpringBootTest
class PayuCreateOrderUseCaseSpec extends Specification implements PayuData {

    @Autowired
    private PayuCreateOrderUseCase createOrderUseCase

    def "should not send CreateOrderRequest and throw InvalidRequestArgumentException instead"() {
        given:
        CreateOrderRequest request = aPartiallyInvalidCreateOrderRequestWithoutCustomerIp()

        when:
        createOrderUseCase.createOrder(request)

        then:
        def ex = thrown(InvalidRequestArgumentException)
        !ex.message.isEmpty()
    }

    @Ignore
    def "should send CreateOrderRequest to PayU"() {
        given:
        CreateOrderRequest request = aValidCreateOrderRequest()

        when:
        def responseOpt = createOrderUseCase.createOrder(request)

        then:
        responseOpt.isPresent()
    }

}