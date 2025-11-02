package lotto.error

interface ErrorType {
    val message: String
    fun fullMessage(): String = "[ERROR] $message"
}
