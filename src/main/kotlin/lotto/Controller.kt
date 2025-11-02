package lotto

import lotto.constant.InputErrorType
import lotto.model.InputParser
import lotto.model.LottoCalculator
import lotto.model.LottoStore
import lotto.model.WinningLotto

class Controller {

    fun run() {
        val purchase = purchaseLotto()
        OutputView.displayPurchaseLottos(purchase.lottos)

        val winningLotto = getWinningLotto()
        val lottoCalculator = LottoCalculator(winningLotto)

        val lottoResult = lottoCalculator.getResult(
            purchase.lottos, purchase.amount
        )
        OutputView.displayWinningStatistics(lottoResult)
    }

    private fun purchaseLotto(): PurchaseHistory {
        OutputView.displayPurchaseAmountPrompt()
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
                OutputView.displayErrorMessage(e.message ?: InputErrorType.UNKNOWN.toString())
                OutputView.displayRetryPrompt()
            }
        }
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumber = getWinningNumber()
        return createWinningLotto(winningNumber)
    }

    private fun getWinningNumber(): Lotto {
        OutputView.displayWinningNumbersPrompt()
        while (true) {
            try {
                val input = InputView.read()
                val numbers = InputParser.parseLottoNumbers(input)
                return Lotto(numbers)
            } catch (e: IllegalArgumentException) {
                OutputView.displayErrorMessage(e.message ?: InputErrorType.UNKNOWN.toString())
                OutputView.displayRetryPrompt()
            }
        }
    }

    private fun createWinningLotto(winningLotto: Lotto): WinningLotto {
        OutputView.displayBonusNumberPrompt()
        while (true) {
            try {
                val input = InputView.read()
                val bonusNumber = InputParser.parseBonusNumber(input)
                return WinningLotto(winningLotto, bonusNumber)
            } catch (e: IllegalArgumentException) {
                OutputView.displayErrorMessage(e.message ?: InputErrorType.UNKNOWN.toString())
                OutputView.displayRetryPrompt()
            }
        }
    }
}