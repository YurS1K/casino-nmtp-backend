package casino.nmtp.web.casinonmtpbackend.models.requests

data class UserEditRequest(
    val login: String,
    val newLogin: String?,
    val password: String?,
)
