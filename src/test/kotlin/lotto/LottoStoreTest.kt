package lotto

import lotto.model.LottoStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoStoreTest {

    @Test
    fun `로또 구입 금액이 로또 금액보다 작으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoStore.purchaseLottos(999)
        }
    }

    @Test
    fun `로또 구입 금액이 1000으로 나누어 떨어지지 않으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoStore.purchaseLottos(1500)
        }
    }

    @Test
    fun `구매 금액만큼 로또를 발행한다`() {
        val lottos = LottoStore.purchaseLottos(5000)
        assertThat(lottos).hasSize(5)
    }
}