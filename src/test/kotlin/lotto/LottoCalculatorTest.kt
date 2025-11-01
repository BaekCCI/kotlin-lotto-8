package lotto

import lotto.model.LottoCalculator
import lotto.model.LottoResult
import lotto.model.Rank
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoCalculatorTest {

    @ParameterizedTest(name = "match = {0}, hasBonus = {1} => {2}")
    @CsvSource(
        "6,false,FIRST",
        "5,true,SECOND",
        "5,false,THIRD",
        "4,false, FOURTH",
        "4,true, FOURTH",
        "3,false, FIFTH",
        "3,true, FIFTH"
    )
    fun `일치하는 개수와 보너스 여부에 따라 Rank를 반환한다`(match: Int, hasBonus: Boolean, expected: Rank) {
        val rank = Rank.of(match, hasBonus)
        assertThat(rank).isEqualTo(expected)
    }

    @ParameterizedTest(name = "match = {0}, hasBonus = {1} => NONE")
    @CsvSource(
        "2,true",
        "1,false",
        "0,true"
    )
    fun `3개 미만으로 일치하면 NONE을 반환한다`(match: Int, hasBonus: Boolean) {
        val rank = Rank.of(match, hasBonus)
        assertThat(rank).isEqualTo(Rank.NONE)
    }

    @Test
    fun `로또 계산 결과에 모든 등수가 존재해야한다`() {
        val rankCount = mapOf(Rank.FIFTH to 2, Rank.THIRD to 1)
        val result = LottoResult.of(rankCount, 0.0)

        assertThat(result.rankCount.keys).containsAll(Rank.entries)
    }

    @ParameterizedTest(name = "당첨:\"1,2,3,4,5,6 + 7\", 구매: \"{0}\" -> {1}")
    @CsvSource(
        "1,2,3,4,5,6|FIRST",
        "1,2,3,4,5,7|SECOND",
        "1,2,3,4,5,8|THIRD",
        "1,2,3,4,7,8|FOURTH",
        "1,2,3,4,8,9|FOURTH",
        "1,2,3,7,8,9|FIFTH",
        "1,2,3,8,9,10|FIFTH",
        "1,2,7,8,9,10|NONE",
        "1,2,8,9,10,11|NONE",
        delimiter = '|'
    )
    fun `구매한 로또와 당첨 로또를 비교하여 등수를 판단한다`(given: String, expected: Rank) {
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7)
        val calculator = LottoCalculator(winningLotto)

        val lotto = Lotto(given.split(",").map { it.toInt() })
        val rank = calculator.getRank(lotto)

        assertThat(rank).isEqualTo(expected)
    }

    @ParameterizedTest(name = "당첨결과 {0} -> 총 수익: {1}")
    @MethodSource("provideRankCounts")
    fun `총 수익을 계산한다`(rankCount: Map<Rank, Int>, expected: Long) {
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7)
        val calculator = LottoCalculator(winningLotto)

        val result = calculator.getTotalPrize(rankCount)
        assertEquals(expected, result)
    }


    @ParameterizedTest(name = "당첨결과 {0}, 구매금액 {1} -> 수익률 {2}%")
    @MethodSource("provideRanksAndPurchaseAmount")
    fun `로또 수익률을 계산한다`(rankCount: Map<Rank, Int>, purchase: Int, expected: Double) {
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7)
        val calculator = LottoCalculator(winningLotto)

        val result = calculator.getProfitRate(rankCount, purchase)
        assertEquals(expected, result, 0.001)
    }


    companion object {
        @JvmStatic
        fun provideRankCounts(): Stream<Arguments> = Stream.of(
            Arguments.of(
                mapOf(Rank.FIRST to 1), 2_000_000_000
            ),
            Arguments.of(
                mapOf(Rank.THIRD to 1), 1_500_000
            ),
            Arguments.of(
                mapOf(Rank.FIFTH to 3), 15_000
            ),
            Arguments.of(
                mapOf(Rank.NONE to 3), 0
            ),
            Arguments.of(
                mapOf(Rank.SECOND to 1, Rank.FOURTH to 1, Rank.FIFTH to 2), 30_060_000.0
            )
        )

        @JvmStatic
        fun provideRanksAndPurchaseAmount(): Stream<Arguments> = Stream.of(
            Arguments.of(
                mapOf(Rank.FIRST to 1), 1000, 200_000_000.0
            ),
            Arguments.of(
                mapOf(Rank.THIRD to 1), 1000, 150_000.0
            ),
            Arguments.of(
                mapOf(Rank.FIFTH to 3), 3000, 500.0
            ),
            Arguments.of(
                mapOf(Rank.NONE to 3), 3000, 0.0
            ),
            Arguments.of(
                mapOf(Rank.SECOND to 1, Rank.FOURTH to 1, Rank.FIFTH to 2), 4000, 751_500.0
            )
        )
    }
}