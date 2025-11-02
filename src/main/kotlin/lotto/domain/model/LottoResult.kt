package lotto.domain.model


data class LottoResult(
    val rankCount: Map<Rank, Int>,
    val profitRate: Double
) {

    companion object {
        fun of(counts: Map<Rank, Int>, profitRate: Double): LottoResult {
            val allRankCounts = Rank.entries.associateWith { counts[it] ?: 0 }
            val sortedRankCounts = allRankCounts.toSortedMap(
                compareByDescending { it.ordinal }
            )

            return LottoResult(sortedRankCounts, profitRate)
        }
    }
}
