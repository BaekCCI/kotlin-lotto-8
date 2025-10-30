package lotto

import lotto.constant.ErrorType
import lotto.constant.LottoConstants.LOTTO_MAX_NUMBER
import lotto.constant.LottoConstants.LOTTO_MIN_NUMBER
import lotto.constant.LottoConstants.LOTTO_SIZE

class Lotto(private val numbers: List<Int>) {

    init {
        validate()
    }

    private fun validate() {
        require(numbers.size == LOTTO_SIZE) { ErrorType.INVALID_LOTTO_SIZE }
        require(numbers.distinct().size == LOTTO_SIZE) { ErrorType.DUPLICATED_LOTTO_NUMBER }
        require(numbers.all { it in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER }) { ErrorType.INVALID_LOTTO_RANGE }
    }

    fun matchCount(other: Lotto): Int {
        return numbers.count { it in other.numbers }
    }

    fun contains(n: Int): Boolean {
        return numbers.contains(n)
    }

    fun numbers(): List<Int> = numbers.toList()
}
