package lotto.domain.model

import lotto.constant.LottoConstants
import lotto.error.LottoErrorType

class Lotto(private val numbers: List<Int>) {

    init {
        validate()
    }

    private fun validate() {
        require(numbers.size == LottoConstants.LOTTO_SIZE) { LottoErrorType.INVALID_LOTTO_SIZE }
        require(numbers.distinct().size == LottoConstants.LOTTO_SIZE) { LottoErrorType.DUPLICATED_LOTTO_NUMBER }
        require(numbers.all { it in LottoConstants.LOTTO_MIN_NUMBER..LottoConstants.LOTTO_MAX_NUMBER }) { LottoErrorType.INVALID_LOTTO_RANGE }
    }

    fun matchCount(other: Lotto): Int {
        return numbers.count { it in other.numbers }
    }

    fun contains(n: Int): Boolean {
        return numbers.contains(n)
    }

    fun numbers(): List<Int> = numbers.sorted()
}
