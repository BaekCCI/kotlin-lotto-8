package lotto.constant

import lotto.constant.LottoConstants.LOTTO_MAX_NUMBER
import lotto.constant.LottoConstants.LOTTO_MIN_NUMBER
import lotto.constant.LottoConstants.LOTTO_PRICE
import lotto.constant.LottoConstants.LOTTO_SIZE

enum class ErrorType(val message: String) {
    INVALID_LOTTO_SIZE("로또 번호는 ${LOTTO_SIZE}개여야 합니다."),
    DUPLICATED_LOTTO_NUMBER("중복된 번호가 있습니다."),
    INVALID_LOTTO_RANGE("로또 번호는 ${LOTTO_MIN_NUMBER}~${LOTTO_MAX_NUMBER} 범위여야 합니다."),

    INVALID_BONUS_NUMBER_RANGE("보너스 번호는 ${LOTTO_MIN_NUMBER}~${LOTTO_MAX_NUMBER} 범위여야 합니다."),
    DUPLICATED_BONUS_NUMBER("당첨 번호와 중복된 번호입니다."),

    INVALID_PURCHASE_COUNT("로또는 1개 이상 구매해야 합니다."),
    INVALID_PURCHASE_AMOUNT("로또 구입 금액은 0원보다 커야 합니다."),
    INVALID_PURCHASE_UNIT("로또 구입 금액은 ${LOTTO_PRICE}원 단위로 입력해야합니다.");

    override fun toString(): String {
        return "$PREFIX $message"
    }

    companion object {
        private const val PREFIX = "[ERROR]"
    }
}