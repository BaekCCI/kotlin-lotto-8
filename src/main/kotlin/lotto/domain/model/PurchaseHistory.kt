package lotto.domain.model

data class PurchaseHistory(
    val amount: Int,
    val lottos: List<Lotto>
)
