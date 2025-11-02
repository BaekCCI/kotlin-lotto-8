package lotto.domain.service

import lotto.constant.LottoConstants.LOTTO_PRICE
import lotto.error.LottoErrorType
import lotto.domain.model.Lotto

object LottoStore {

    fun purchaseLottos(amount: Int): List<Lotto> {
        val count = amount.toLottoCount()
        return LottoMachine.generateLottos(count)
    }

    private fun Int.toLottoCount(): Int {
        validateAmount(this)
        return this / LOTTO_PRICE
    }

    private fun validateAmount(amount: Int) {
        require(amount >= LOTTO_PRICE) { LottoErrorType.INVALID_PURCHASE_AMOUNT }
        require(amount % LOTTO_PRICE == 0) { LottoErrorType.INVALID_PURCHASE_UNIT }
    }
}
