package lotto.constant

interface ErrorType {
    val message: String
    fun fullMessage(): String = "[ERROR] $message"
}