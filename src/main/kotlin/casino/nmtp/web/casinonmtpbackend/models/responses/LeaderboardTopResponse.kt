package casino.nmtp.web.casinonmtpbackend.models.responses

import casino.nmtp.web.casinonmtpbackend.entities.Leaderboard

data class LeaderboardTopResponse(
    val leaderboard: List<Leaderboard>,
)
