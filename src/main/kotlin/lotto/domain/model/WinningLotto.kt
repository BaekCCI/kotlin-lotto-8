package lotto.domain.model

import lotto.constant.LottoConstants
import lotto.error.LottoErrorType

data class WinningLotto(
    val lotto: Lotto,
    val bonusNumber: Int
) {
    init {
        require(
            bonusNumber in LottoConstants.LOTTO_MIN_NUMBER..LottoConstants.LOTTO_MAX_NUMBER
        ) { LottoErrorType.INVALID_BONUS_NUMBER_RANGE }

        require(!lotto.contains(bonusNumber)) {
            LottoErrorType.DUPLICATED_BONUS_NUMBER
        }
    }
}
