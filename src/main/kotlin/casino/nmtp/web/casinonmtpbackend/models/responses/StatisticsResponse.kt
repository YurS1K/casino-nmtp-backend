package casino.nmtp.web.casinonmtpbackend.models.responses

data class StatisticsResponse(
    val totalGames: Long,
    val totalWins: Long,
    val totalLosses: Long,
    val favoriteGames: String?,
)
