package pl.techgarden.tasks.payu

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.techgarden.tasks.payu.usecases.CreateStandardOrder
import spock.lang.Specification

@SpringBootTest
class CreateStandardOrderSpec extends Specification implements PayuData {

    @Autowired
    private CreateStandardOrder createStandardOrder

    def "should prepare and execute CreateStandardOrder command"() {
        when:
        def responseOpt = createStandardOrder.requestBuilder()
                .customerIp("127.0.0.1")
                .merchantPosId(145227)
                .description("RTV market")
                .currencyCode(CreateOrderRequest.CurrencyCode.PLN)
                .products(aMouseProduct())
                .buildAndExecute()

        then:
        responseOpt.isPresent()
        responseOpt.get().status.isOk()
    }

    def "should prepare and throw exception for invalid/incomplete CreateStandardOrder command"() {
        when:
        createStandardOrder.requestBuilder()
                .merchantPosId(145227)
                .description("RTV market")
                .currencyCode(CreateOrderRequest.CurrencyCode.PLN)
                .products(aMouseProduct())
                .buildAndExecute()

        then:
        thrown(IllegalArgumentException)
    }

}