package lotto

import lotto.constant.OutputMessage

object OutputView {

    private fun prompt(message: OutputMessage) {
        println(message)
    }

    fun displayPurchaseAmountPrompt() = prompt(OutputMessage.PURCHASE_AMOUNT_PROMPT)

    fun displayWinningNumbersPrompt() = prompt(OutputMessage.WINNING_NUMBERS_PROMPT)

    fun displayBonusNumberPrompt() = prompt(OutputMessage.BONUS_NUMBER_PROMPT)
}