package lotto

import lotto.constant.ErrorType
import lotto.constant.OutputMessage
import lotto.model.LottoResult
import lotto.model.Rank
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.iterator

object OutputView {

    private fun prompt(message: OutputMessage) {
        println(message)
    }

    fun displayPurchaseAmountPrompt() = prompt(OutputMessage.PURCHASE_AMOUNT_PROMPT)

    fun displayWinningNumbersPrompt() = prompt(OutputMessage.WINNING_NUMBERS_PROMPT)

    fun displayBonusNumberPrompt() = prompt(OutputMessage.BONUS_NUMBER_PROMPT)

    fun displayPurchaseLottos(lottos: List<List<Int>>) {
        println(OutputMessage.PURCHASED_COUNT.format(lottos.size))
        lottos.forEach { lotto ->
            println(lotto.joinToString(", ", "[", "]"))
        }
    }

    fun displayWinningStatistics(result: LottoResult) {
        println(OutputMessage.WINNING_STATISTIC_TITLE)
        displayRankMatch(result.rankCount)
        displayProfitRate(result.profitRate)
    }

    private fun displayRankMatch(rankCount: Map<Rank, Int>) {
        for ((rank, count) in rankCount) {
            if (rank == Rank.NONE) continue
            val matchCount = rank.matchCount
            val formattedPrize = "%,d".format(rank.prize)

            val message = if (rank.hasBonus) {
                OutputMessage.BONUS_MATCH
            } else {
                OutputMessage.NORMAL_MATCH
            }
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
}