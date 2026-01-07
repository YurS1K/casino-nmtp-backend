package casino.nmtp.web.casinonmtpbackend.models.responses

import casino.nmtp.web.casinonmtpbackend.entities.GameHistory

data class LastGamesResponse(
    val games: List<GameHistory>?,
)
