package casino.nmtp.web.casinonmtpbackend.models.responses

import java.time.LocalDate

data class UserInfoResponse(
    var login: String,
    var registrationDate: LocalDate,
    var balance: Long,
)
