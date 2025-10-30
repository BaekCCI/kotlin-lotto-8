package lotto

import lotto.model.LottoStore
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoStoreTest {

    @Test
    fun `로또 구입 금액이 0보다 작으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoStore.purchaseLottos(0)
        }
    }

    @Test
    fun `로또 구입 금액이 1000으로 나누어 떨어지지 않으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoStore.purchaseLottos(1500)
        }
    }

}