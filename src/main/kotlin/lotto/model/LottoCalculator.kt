package lotto.model

import lotto.Lotto

class LottoCalculator(
    private val winningLotto: WinningLotto
) {
    fun getRank(lotto: Lotto): Rank {
        val matchCount = lotto.matchCount(winningLotto.lotto)
        val hasBonus = lotto.contains(winningLotto.bonusNumber)

        return Rank.of(matchCount, hasBonus)
    }

    fun getTotalPrize(rankCount: Map<Rank, Int>): Long {
        var total = 0L
        rankCount.entries.forEach { (rank, count) ->
            total += rank.prize * count
        }
        return total
    }

    fun getProfitRate(rankCount: Map<Rank, Int>, purchaseAmount: Int): Double {
        val total = getTotalPrize(rankCount).toDouble()

        return total / purchaseAmount * 100
    }
}
