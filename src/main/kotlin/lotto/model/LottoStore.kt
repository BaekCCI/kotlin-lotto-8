package lotto.model

import lotto.Lotto

object LottoStore {
    fun purchaseLottos(amount: Int): List<Lotto> {
        val count = amount.toCount()

        return LottoMachine.generateLottos(count)
    }

    private fun Int.toCount(): Int {
        validateAmount(this)
        return this / 1000
    }

    private fun validateAmount(amount: Int) {
        require(amount > 0)
        require(amount % 1000 == 0)
    }
}