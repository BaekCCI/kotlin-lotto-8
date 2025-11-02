package lotto

import lotto.constant.OutputMessage

object OutputView {

    private fun prompt(message: OutputMessage) {
        println(message)
    }

    fun displayPurchaseAmountPrompt() = prompt(OutputMessage.PURCHASE_AMOUNT_PROMPT)

    fun displayWinningNumbersPrompt() = prompt(OutputMessage.WINNING_NUMBERS_PROMPT)

    fun displayBonusNumberPrompt() = prompt(OutputMessage.BONUS_NUMBER_PROMPT)

    fun displayPurchaseLottos(lottos: List<List<Int>>) {
        println("${lottos.size}개를 구매했습니다.")
        lottos.forEach { lotto ->
            println(lotto.joinToString(", ", "[", "]"))
        }
    }
}