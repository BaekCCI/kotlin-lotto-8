package lotto.model

import lotto.Lotto
import lotto.constant.LottoErrorType
import lotto.constant.LottoConstants.LOTTO_MAX_NUMBER
import lotto.constant.LottoConstants.LOTTO_MIN_NUMBER

data class WinningLotto(
    val lotto: Lotto,
    val bonusNumber : Int
){
    init {
        require(bonusNumber in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER){ LottoErrorType.INVALID_BONUS_NUMBER_RANGE }
        require(!lotto.contains(bonusNumber)){ LottoErrorType.DUPLICATED_BONUS_NUMBER }
    }
}
