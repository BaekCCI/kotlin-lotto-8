package lotto.constant

enum class OutputMessage(val text: String) {
    PURCHASE_AMOUNT_PROMPT("구입금액을 입력해 주세요."),
    WINNING_NUMBERS_PROMPT("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_PROMPT("보너스 번호를 입력해 주세요.");

    override fun toString(): String = text
}