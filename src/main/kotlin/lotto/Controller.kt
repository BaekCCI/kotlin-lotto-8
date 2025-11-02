package lotto

import lotto.model.InputParser
import lotto.model.LottoCalculator
import lotto.model.LottoResult
import lotto.model.LottoStore
import lotto.model.WinningLotto

class Controller {

    fun run() {
        val purchaseHistory = purchaseLotto()
        OutputView.displayPurchaseLottos(purchaseHistory.lotto.map { it.numbers().sorted() })
        val winningLotto = getWinningLotto()

        val lottoCalculator = LottoCalculator(winningLotto)

        val lottoResult = lottoCalculator.getResult(purchaseHistory.lotto, purchaseHistory.amount)
        OutputView.displayWinningStatistics(lottoResult)
    }

    private fun purchaseLotto(): PurchaseHistory {
        while (true) {
            try {
                val input = InputView.read()
                val purchaseAmount = InputParser.parseAmount(input)
                val lottos = LottoStore.purchaseLottos(purchaseAmount)
                return PurchaseHistory(
                    purchaseAmount,
                    lottos
                )
            } catch (e: IllegalArgumentException) {
                OutputView.displayErrorMessage(e.message ?: "")
            }
        }
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumber = getWinningNumber()
        while (true) {
            try {
                val input = InputView.read()
                val bonusNumber = InputParser.parseBonusNumber(input)
                return WinningLotto(winningNumber, bonusNumber)
            } catch (e: IllegalArgumentException) {
                OutputView.displayErrorMessage(e.message ?: "")
            }
        }
    }

    private fun getWinningNumber(): Lotto {
        while (true) {
            try {
                val input = InputView.read()
                val numbers = InputParser.parseLottoNumbers(input)
                return Lotto(numbers)
            } catch (e: IllegalArgumentException) {
                OutputView.displayErrorMessage(e.message ?: "")
            }
        }
    }
}