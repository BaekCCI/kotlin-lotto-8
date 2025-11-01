package lotto.model

object InputParser {
    const val MONEY_ALLOWED_CHAR_REGEX = "[\\d,]+"
    const val MONEY_THOUSAND_FORMAT_REGEX = "^\\d{1,3}(,\\d{3})*$|^d+"

    fun parseAmount(input: String): Int {
        require(input.isNotBlank())
        require(Regex(MONEY_ALLOWED_CHAR_REGEX).matches(input))
        require(Regex(MONEY_THOUSAND_FORMAT_REGEX).matches(input))

        return 0
    }
}