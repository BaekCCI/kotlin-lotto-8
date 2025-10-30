package lotto

import lotto.model.LottoMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoMachineTest {

    @Test
    fun `로또 발행 개수가 0보다 작으면 예외가 발생한다`(){
        assertThrows<IllegalArgumentException> {
            LottoMachine.generateLottos(0)
        }
    }
    @Test
    fun `구입한 개수만큼 로또를 생성한다`() {
        val lottos = LottoMachine.generateLottos(3)
        assertThat(lottos).hasSize(3)
    }

    @Test
    fun `생성된 로또는 중복되지 않는 6개의 번호를 갖는다`() {
        val lottos = LottoMachine.generateLottos(1)

        assertThat(lottos[0].numbers())
            .hasSize(6)
            .doesNotHaveDuplicates()
    }

    @Test
    fun `생성된 로또번호는 모두 1~45 범위에 있어야 한다`() {
        val lottos = LottoMachine.generateLottos(1)
        lottos[0].numbers().forEach {
            assertThat(it).isBetween(1, 45)
        }
    }
}