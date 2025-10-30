package lotto

import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

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

    @ParameterizedTest
    @CsvSource(
        "1,2,3,4,5,6 | 1,2,3,7,8,9 | 3",
        "1,2,3,4,5,6 | 7,8,9,10,11,12 | 0",
        "1,2,3,4,5,6 | 1,2,3,4,5,6 | 6",
        delimiter = '|'
    )
    fun `겹치는 로또 번호의 개수를 반환한다`(a: String, b: String, expected: Int) {
        val lotto = Lotto(a.split(",").map { it.toInt() })
        val other = Lotto(b.split(",").map { it.toInt() })
        val matchCount = lotto.matchCount(other)

        assertThat(matchCount).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 3, 5])
    fun `번호가 포함되어 있으면 true를 반환한다`(given: Int) {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val result = lotto.contains(given)

        assertThat(result).isTrue()
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 3, 5])
    fun `번호가 포함되어 있지 않으면 false를 반환한다`(given: Int) {
        val lotto = Lotto(listOf(2, 4, 6, 8, 10, 12))
        val result = lotto.contains(given)

        assertThat(result).isFalse()
    }

    @ParameterizedTest(name = "invalid number = {0}")
    @ValueSource(ints = [0, 46])
    fun `보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다`(given: Int){
        val winningNumbers = Lotto(listOf(1,2,3,4,5,6))

        assertThrows<IllegalArgumentException> {
            WinningLotto(winningNumbers, given)
        }
    }
    @Test
    fun `보너스 번호가 당첨 번호에 존재하면 예외가 발생한다`(){
        val winningNumbers = Lotto(listOf(1,2,3,4,5,6))

        assertThrows<IllegalArgumentException> {
            WinningLotto(winningNumbers, 3)
        }
    }
}
