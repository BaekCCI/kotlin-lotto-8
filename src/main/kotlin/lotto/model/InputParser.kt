package lotto.model

import lotto.constant.InputErrorType

object InputParser {
    const val MONEY_ALLOWED_CHAR_REGEX = "[\\d,]+"
    const val MONEY_THOUSAND_FORMAT_REGEX = "^\\d{1,3}(,\\d{3})*$|^\\d+$"

    fun parseAmount(input: String): Int {
        validateAmountInput(input)

        val cleanedInput = input.replace(",", "")
        val amount = cleanedInput.toInt()
        require(amount > 0) { InputErrorType.NON_POSITIVE_AMOUNT }

        return amount
    }

    fun validateAmountInput(input: String) {
        require(input.isNotBlank()) { InputErrorType.EMPTY_AMOUNT }
        require(Regex(MONEY_ALLOWED_CHAR_REGEX).matches(input)) { InputErrorType.INVALID_CHARACTER_AMOUNT }
        require(Regex(MONEY_THOUSAND_FORMAT_REGEX).matches(input)) { InputErrorType.INVALID_AMOUNT_FORMAT }
    }

    fun parseLottoNumbers(input: String): List<Int> {
        require(input.isNotBlank()) { InputErrorType.EMPTY_WINNING_NUMBERS }

        val parsedInput = input.split(",").map {
            require(it.isNotBlank()) { InputErrorType.EMPTY_VALUE_INCLUDED }
            requireNotNull(it.toIntOrNull()) { InputErrorType.INVALID_NUMBER_FORMAT }
        }

        return parsedInput
    }
    fun parseBonusNumber(input:String):Int{
        return 0
    }

}