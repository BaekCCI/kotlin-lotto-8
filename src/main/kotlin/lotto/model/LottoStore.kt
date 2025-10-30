package lotto.model

import lotto.Lotto

object LottoStore {
    fun purchaseLottos(amount: Int): List<Lotto> {
        validateAmount(amount)
        return emptyList()
    }

    private fun validateAmount(amount: Int) {
        require(amount > 0)
        require(amount % 1000 == 0)
    }
}