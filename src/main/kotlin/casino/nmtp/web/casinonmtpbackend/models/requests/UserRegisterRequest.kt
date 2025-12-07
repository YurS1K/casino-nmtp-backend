package casino.nmtp.web.casinonmtpbackend.models.requests

data class UserRegisterRequest (
    val login : String,
    val password : String,
)