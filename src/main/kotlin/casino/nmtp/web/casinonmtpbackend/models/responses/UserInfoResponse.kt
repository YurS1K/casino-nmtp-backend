package casino.nmtp.web.casinonmtpbackend.models.responses

import casino.nmtp.web.casinonmtpbackend.enums.UserRole
import java.time.LocalDate


data class UserInfoResponse(
    var username: String,
    var registrationDate: LocalDate,
    var balance: Long,
    var role: UserRole,
)
