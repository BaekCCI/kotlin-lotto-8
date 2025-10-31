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
}