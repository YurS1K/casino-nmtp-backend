package casino.nmtp.web.casinonmtpbackend.services

import casino.nmtp.web.casinonmtpbackend.entities.User
import casino.nmtp.web.casinonmtpbackend.enums.UserRole
import casino.nmtp.web.casinonmtpbackend.models.responses.UserAuthorizationResponse
import casino.nmtp.web.casinonmtpbackend.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserService (
    val userRepository: UserRepository
) {
    fun userAuthorization(login: String, password: String): ResponseEntity<UserAuthorizationResponse> {
        val user = userRepository.authorization(login, password)
        if (user != null) {
            return ResponseEntity(UserAuthorizationResponse(user.id), HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
    }

    fun userRegistration(login: String, password: String, username: String): ResponseEntity<UserAuthorizationResponse> {
        val user = User(
            login = login,
            password = password,
            username = username,
            role = UserRole.USER,
            registrationDate = LocalDate.now()
        )
        userRepository.save(user)
        return ResponseEntity(UserAuthorizationResponse(user.id), HttpStatus.CREATED)
    }
}