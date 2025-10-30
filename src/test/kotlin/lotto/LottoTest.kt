package lotto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    // TODO: 테스트가 통과하도록 프로덕션 코드 구현
    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 5))
        }
    }

    @ParameterizedTest(name = "invalid number = {0}")
    @ValueSource(ints = [0, 46])
    fun `로또 번호가 1~45 범위를 벗어나면 예외가 발생한다`(given: Int) {
        val numbers = listOf(given, 2, 3, 4, 5, 6)
        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }

}
