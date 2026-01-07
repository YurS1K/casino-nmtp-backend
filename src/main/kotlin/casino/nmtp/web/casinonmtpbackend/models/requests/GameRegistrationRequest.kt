package casino.nmtp.web.casinonmtpbackend.models.requests

data class GameRegistrationRequest(
    val game: String,
    val login: String,
    val winLostAmount: Long,
    val betAmount: Long,
)
