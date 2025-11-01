package lotto.model

import lotto.Lotto
import lotto.constant.ErrorType
import lotto.constant.LottoConstants.LOTTO_PRICE

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
        require(amount > LOTTO_PRICE) { ErrorType.INVALID_PURCHASE_AMOUNT }
        require(amount % LOTTO_PRICE == 0) { ErrorType.INVALID_PURCHASE_UNIT }
    }
}