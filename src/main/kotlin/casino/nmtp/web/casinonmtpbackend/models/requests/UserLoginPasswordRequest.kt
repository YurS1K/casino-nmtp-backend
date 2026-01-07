package casino.nmtp.web.casinonmtpbackend.models.requests

data class UserLoginPasswordRequest(
    val login: String,
    val password: String,
)
