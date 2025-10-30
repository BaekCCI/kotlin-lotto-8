package lotto.model

import camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange
import lotto.Lotto

object LottoMachine {

    fun generateLottos(amount: Int): List<Lotto> {
        require(amount > 0)

        return List(amount) {
            val numbers = pickUniqueNumbersInRange(1, 45, 6)
            Lotto(numbers)
        }
    }
}