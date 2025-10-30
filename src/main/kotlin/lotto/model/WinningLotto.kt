package lotto.model

import lotto.Lotto

data class WinningLotto(
    val lotto: Lotto,
    val bonusNumber : Int
){
    init {
        require(bonusNumber in 1..45)
        require(!lotto.contains(bonusNumber))
    }
}
