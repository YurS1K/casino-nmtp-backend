package casino.nmtp.web.casinonmtpbackend.enums

enum class UserRole {
    USER, ADMIN
}

fun String.toUserRole(): UserRole =
    when (this) {
        "ADMIN" -> UserRole.ADMIN
        "USER" -> UserRole.USER
        else -> UserRole.USER
    }