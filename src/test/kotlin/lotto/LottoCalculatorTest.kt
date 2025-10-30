package lotto

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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

}