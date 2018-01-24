package pl.techgarden.tasks.payu

import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.techgarden.tasks.payu.request.CreateOrderRequest
import pl.techgarden.tasks.payu.response.CreateOrderResponse
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

    def "should send CreateOrderRequest to PayU"() {
        given:
        CreateOrderRequest request = aValidCreateOrderRequest()

        when:
        CreateOrderResponse response = createOrderUseCase.createOrder(request)

        then:
        thrown(FeignException)
    }

}