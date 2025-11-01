package lotto.constant

enum class InputErrorType(val message: String) {
    EMPTY_AMOUNT("구입할 금액이 입력되지 않았습니다."),
    INVALID_CHARACTER_AMOUNT("유효하지 않은 문자가 포함되어 있습니다."),
    INVALID_AMOUNT_FORMAT("천 단위 구분 쉼표(,)의 위치가 올바르지 않습니다."),
    NON_POSITIVE_AMOUNT("구입 금액은 0원보다 커야 합니다."),

    EMPTY_WINNING_NUMBERS("로또 번호가 입력되지 않았습니다."),
    EMPTY_VALUE_INCLUDED("[ERROR] 빈 값이 포함되어 있습니다."),
    INVALID_NUMBER_FORMAT("[ERROR] 숫자가 아닌 값이 포함되어 있습니다.");

    override fun toString(): String {
        return "$PREFIX $message"
    }

    companion object {
        private const val PREFIX = "[ERROR]"
    }
}