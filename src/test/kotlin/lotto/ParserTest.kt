package lotto

import lotto.model.InputParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ParserTest {

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "    "])
    fun `금액 입력 시 입력값이 비어있으면 예외 발생`(given: String) {
        assertThrows<IllegalArgumentException> {
            InputParser.parseAmount(given)
        }
    }

    @Test
    fun `구입 금액 입력 시 숫자 및 쉼표를 제외한 문자가 포함되면 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            InputParser.parseAmount("132@")
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["123,4", "12,34", ",1234", "1234,"])
    fun `금액 입력 시 천 단위 구분 쉼표의 위치가 올바르지 않은 경우 예외 발생`(given: String) {
        assertThrows<IllegalArgumentException> {
            InputParser.parseAmount(given)
        }
    }

    @ParameterizedTest(name = "input: {0}")
    @CsvSource("123|123", "1,234|1234", "1,234,567|1234567", "123,456,789|123456789", delimiter = '|')
    fun `입력된 금액을 숫자로 변환`(given: String, expected: Int) {
        val result = InputParser.parseAmount(given)
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `입력된 금액이 0보다 작으면 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            InputParser.parseAmount("-1")
        }
    }


}