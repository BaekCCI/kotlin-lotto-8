package lotto.domain.service

import lotto.domain.model.*

class LottoCalculator(private val winningLotto: WinningLotto) {

    fun getRank(lotto: Lotto): Rank {
        val matchCount = lotto.matchCount(winningLotto.lotto)
        val hasBonus = lotto.contains(winningLotto.bonusNumber)

        return Rank.of(matchCount, hasBonus)
    }

    fun getTotalPrize(rankCount: Map<Rank, Int>): Long {
        var total = 0L
        rankCount.entries.forEach { (rank, count) ->
            total += rank.prize.toLong() * count
        }

        return total
    }

    fun getProfitRate(rankCount: Map<Rank, Int>, purchaseAmount: Int): Double {
        val total = getTotalPrize(rankCount).toDouble()

        return total / purchaseAmount * 100
    }

    fun getResult(lottos: List<Lotto>, purchaseAmount: Int): LottoResult {
        val ranks = lottos.map { getRank(it) }
            .groupingBy { it }
            .eachCount()

        val profitRate = getProfitRate(ranks, purchaseAmount)

        return LottoResult.of(ranks, profitRate)
    }
}
