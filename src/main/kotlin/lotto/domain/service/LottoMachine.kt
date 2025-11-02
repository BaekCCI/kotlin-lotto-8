package lotto.domain.service

import camp.nextstep.edu.missionutils.Randoms
import lotto.constant.LottoConstants.LOTTO_MAX_NUMBER
import lotto.constant.LottoConstants.LOTTO_MIN_NUMBER
import lotto.constant.LottoConstants.LOTTO_SIZE
import lotto.error.LottoErrorType
import lotto.domain.model.Lotto

object LottoMachine {

    fun generateLottos(count: Int): List<Lotto> {
        require(count > 0) { LottoErrorType.INVALID_PURCHASE_COUNT }

        return List(count) { generateLotto() }
    }

    private fun generateLotto(): Lotto {
        val numbers = generateLottoNumbers()
        return Lotto(numbers)
    }

    private fun generateLottoNumbers(): List<Int> {
        return Randoms.pickUniqueNumbersInRange(
            LOTTO_MIN_NUMBER,
            LOTTO_MAX_NUMBER,
            LOTTO_SIZE
        )
    }
}
