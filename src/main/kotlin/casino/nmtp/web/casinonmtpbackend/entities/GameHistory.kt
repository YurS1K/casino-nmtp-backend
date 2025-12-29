package casino.nmtp.web.casinonmtpbackend.entities

import java.time.LocalDate

data class GameHistory(
    val date: LocalDate,
    val winLostAmount: Long,
)
