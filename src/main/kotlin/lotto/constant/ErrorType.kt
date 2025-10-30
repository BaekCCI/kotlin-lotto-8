package lotto.constant

import lotto.constant.LottoConstants.LOTTO_MAX_NUMBER
import lotto.constant.LottoConstants.LOTTO_MIN_NUMBER
import lotto.constant.LottoConstants.LOTTO_SIZE

enum class ErrorType(val message: String) {
    INVALID_LOTTO_SIZE("로또 번호는 ${LOTTO_SIZE}개여야 합니다."),
    DUPLICATED_LOTTO_NUMBER("중복된 번호가 있습니다."),
    INVALID_LOTTO_RANGE("로또 번호는 ${LOTTO_MIN_NUMBER}~${LOTTO_MAX_NUMBER} 범위여야 합니다.");

    override fun toString(): String {
        return "$PREFIX $message"
    }

    companion object {
        private const val PREFIX = "[ERROR]"
    }
}