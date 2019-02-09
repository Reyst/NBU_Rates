package reyst.gsihome.rates

import org.junit.Test

import org.junit.Assert.*
import org.simpleframework.xml.core.Persister
import reyst.gsihome.rates.data.remote.retrofit.CurrencyDTO
import reyst.gsihome.rates.data.remote.retrofit.ResponseDTO

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testSimpleXML() {
        val responseDTO = ResponseDTO()

        responseDTO.currency = listOf(
            CurrencyDTO().apply {
                id = 1
                name = "1test"
                rate = 1.1
                code = "111"
                date = "01.02.1011"
            },
            CurrencyDTO().apply {
                id = 2
                name = "2test"
                rate = 2.2
                code = "222"
                date = "02.02.2022"
            }
        )

        Persister().write(
            responseDTO,
            System.out
        )
    }

}
