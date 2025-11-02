package lotto.constant

enum class OutputMessage(val text: String) {
    PURCHASE_AMOUNT_PROMPT("구입금액을 입력해 주세요."),
    WINNING_NUMBERS_PROMPT("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_PROMPT("보너스 번호를 입력해 주세요."),

    PURCHASED_COUNT("%d개를 구매했습니다."),

    WINNING_STATISTIC_TITLE("당첨 통계\n---"),
    NORMAL_MATCH("%d개 일치 (%s원) - %d개"),
    BONUS_MATCH("%d개 일치, 보너스 볼 일치 (%s원) - %d개"),
    PROFIT_RATE("총 수익률은 %s%%입니다.");

    override fun toString(): String = text

    fun format(vararg args: Any): String = text.format(*args)
}