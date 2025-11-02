package lotto

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import lotto.domain.model.Lotto
import lotto.error.LottoErrorType
import lotto.domain.model.LottoResult
import lotto.domain.model.Rank
import lotto.view.InputView
import lotto.view.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ViewTest : NsTest() {
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

    @Test
    fun `발행한 로또 수량 및 번호를 출력한다`() {
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),
            Lotto(listOf(7, 8, 9, 10, 11, 12))
        )
        assertSimpleTest {
            OutputView.displayPurchaseLottos(lottos)
            assertThat(output()).contains(
                "2개를 구매했습니다.",
                "[1, 2, 3, 4, 5, 6]",
                "[7, 8, 9, 10, 11, 12]"
            )
        }
    }

    @Test
    fun `당첨 통계를 출력한다`() {
        val lottoResult = LottoResult(
            rankCount = mapOf(
                Rank.FIRST to 0,
                Rank.SECOND to 1,
                Rank.THIRD to 0,
                Rank.FOURTH to 1,
                Rank.FIFTH to 2,
                Rank.NONE to 3
            ),
            429_428.571
        )
        assertSimpleTest {
            OutputView.displayWinningStatistics(lottoResult)
            assertThat(output()).contains(
                "당첨 통계",
                "---",
                "3개 일치 (5,000원) - 2개",
                "4개 일치 (50,000원) - 1개",
                "5개 일치 (1,500,000원) - 0개",
                "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
                "6개 일치 (2,000,000,000원) - 0개",
                "총 수익률은 429428.6%입니다."
            )
        }
    }

    @Test
    fun `에러 메세지를 출력 시 ERROR로 시작한다`() {
        assertSimpleTest {
            OutputView.displayErrorMessage(LottoErrorType.INVALID_LOTTO_SIZE.toString())
            assertThat(output()).startsWith("[ERROR]")
        }
    }

    @Test
    fun `입력값을 그대로 반환한다`() {
        run("1234")
        val result = InputView.read()
        assertThat(result).isEqualTo("1234")
    }

    override fun runMain() {}
}
