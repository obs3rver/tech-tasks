package pl.techgarden.tasks.payu

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class PayuCreateOrderUseCaseSpec extends Specification implements PayuData {

    @Autowired
    private PayuCreateOrderUseCase createOrderUseCase

    def "should not send CreateOrderRequest and throw InvalidRequestArgumentException instead"() {
        given:
        CreateOrderRequest request = aPartiallyInvalidCreateOrderRequestWithoutCustomerIp()

        when:
        createOrderUseCase.createStandardOrder(request)

        then:
        thrown(InvalidRequestArgumentException)
    }

    def "should send CreateOrderRequest to PayU"() {
        given:
        CreateOrderRequest request = aValidCreateOrderRequest()

        when:
        def responseOpt = createOrderUseCase.createStandardOrder(request)

        then:
        responseOpt.isPresent()
        responseOpt.get().status.isOk()
    }

}