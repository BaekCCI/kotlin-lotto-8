package lotto.model

import camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange
import lotto.Lotto
import lotto.constant.ErrorType
import lotto.constant.LottoConstants.LOTTO_MAX_NUMBER
import lotto.constant.LottoConstants.LOTTO_MIN_NUMBER
import lotto.constant.LottoConstants.LOTTO_SIZE

object LottoMachine {

    fun generateLottos(count: Int): List<Lotto> {
        require(count > 0) { ErrorType.INVALID_PURCHASE_COUNT }

        return List(count) { generateLotto() }
    }

    private fun generateLotto(): Lotto {
        val numbers = generateLottoNumbers()
        return Lotto(numbers)
    }

    private fun generateLottoNumbers(): List<Int> {
        return pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_SIZE)
    }
}