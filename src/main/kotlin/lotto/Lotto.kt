package lotto

class Lotto(private val numbers: List<Int>) {
    init {
        validate()
    }

    private fun validate() {
        require(numbers.size == 6) { "[ERROR] 로또 번호는 6개여야 합니다." }
        require(numbers.distinct().size == 6) { "[ERROR] 중복된 번호가 있습니다." }
        require(numbers.all { it in 1..45 }) { "[ERROR] 로또 번호는 1~45 범위여야 합니다." }
    }

    fun matchCount(other: Lotto): Int {
        return numbers.count { it in other.numbers }
    }

    fun contains(n: Int): Boolean {
        return numbers.contains(n)
    }
}
