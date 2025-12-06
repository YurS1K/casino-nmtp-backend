package casino.nmtp.web.casinonmtpbackend.models.requests

data class UserAuthorizationRequest(
    val login: String,
    val password: String
)
