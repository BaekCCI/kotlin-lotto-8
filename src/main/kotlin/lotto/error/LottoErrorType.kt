package lotto.error

import lotto.constant.LottoConstants

enum class LottoErrorType(override val message: String) : ErrorType {

    //로또 번호 관련 에러
    INVALID_LOTTO_SIZE("로또 번호는 ${LottoConstants.LOTTO_SIZE}개여야 합니다."),
    DUPLICATED_LOTTO_NUMBER("중복된 번호가 있습니다."),
    INVALID_LOTTO_RANGE("로또 번호는 ${LottoConstants.LOTTO_MIN_NUMBER}~${LottoConstants.LOTTO_MAX_NUMBER} 범위여야 합니다."),

    //보너스 번호 관련 에러
    INVALID_BONUS_NUMBER_RANGE("보너스 번호는 ${LottoConstants.LOTTO_MIN_NUMBER}~${LottoConstants.LOTTO_MAX_NUMBER} 범위여야 합니다."),
    DUPLICATED_BONUS_NUMBER("당첨 번호와 중복된 번호입니다."),

    //금액/개수 관련 에러
    INVALID_PURCHASE_COUNT("로또는 1개 이상 구매해야 합니다."),
    INVALID_PURCHASE_AMOUNT("로또 구입 금액은 ${LottoConstants.LOTTO_PRICE}원보다 커야 합니다."),
    INVALID_PURCHASE_UNIT("로또 구입 금액은 ${LottoConstants.LOTTO_PRICE}원 단위로 입력해야합니다.");

    override fun toString(): String = fullMessage()
}
