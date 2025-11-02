package lotto

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutputViewTest : NsTest() {
    @Test
    fun `구입금액 입력 안내 메세지를 출력한다`() {
        assertSimpleTest {
            OutputView.displayPurchaseAmountPrompt()
            assertThat(output()).contains("구입금액을 입력해 주세요.")
        }
    }

    @Test
    fun `로또번호 입력 안내 메세지를 출력한다`() {
        assertSimpleTest {
            OutputView.displayWinningNumbersPrompt()
            assertThat(output()).contains("당첨 번호를 입력해 주세요.")
        }
    }

    @Test
    fun `보너스 번호 입력 안내 메세지를 출력한다`() {
        assertSimpleTest {
            OutputView.displayBonusNumberPrompt()
            assertThat(output()).contains("보너스 번호를 입력해 주세요.")
        }
    }


    override fun runMain() {}
}