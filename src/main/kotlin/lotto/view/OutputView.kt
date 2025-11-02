package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.LottoResult
import lotto.domain.model.Rank
import kotlin.collections.iterator

object OutputView {

    private fun prompt(message: OutputMessage) {
        println(message)
    }

    fun displayPurchaseAmountPrompt() = prompt(OutputMessage.PURCHASE_AMOUNT_PROMPT)

    fun displayWinningNumbersPrompt() = prompt(OutputMessage.WINNING_NUMBERS_PROMPT)

    fun displayBonusNumberPrompt() = prompt(OutputMessage.BONUS_NUMBER_PROMPT)

    fun displayPurchaseLottos(lottos: List<Lotto>) {
        println()
        println(OutputMessage.PURCHASED_COUNT.format(lottos.size))

        lottos.forEach { lotto ->
            println(lotto.numbers().joinToString(", ", "[", "]"))
        }
        println()
    }

    fun displayWinningStatistics(result: LottoResult) {
        println()
        println(OutputMessage.WINNING_STATISTIC_TITLE)

        displayRankMatch(result.rankCount)
        displayProfitRate(result.profitRate)
    }

    private fun displayRankMatch(rankCount: Map<Rank, Int>) {
        for ((rank, count) in rankCount) {
            if (rank == Rank.NONE) continue

            val matchCount = rank.matchCount
            val formattedPrize = "%,d".format(rank.prize)

            val message =
                if (rank.hasBonus) OutputMessage.BONUS_MATCH
                else OutputMessage.NORMAL_MATCH

            println(message.format(matchCount, formattedPrize, count))
        }
    }

    private fun displayProfitRate(profitRate: Double) {
        val formattedProfitRate = "%.1f".format(profitRate)

        println(OutputMessage.PROFIT_RATE.format(formattedProfitRate))
    }

    fun displayErrorMessage(message: String) {
        println(message)
    }

    fun displayRetryPrompt() {
        println(OutputMessage.RETRY_PROMPT)
    }
}
