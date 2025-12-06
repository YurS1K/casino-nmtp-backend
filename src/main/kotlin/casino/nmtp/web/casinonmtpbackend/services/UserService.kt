package casino.nmtp.web.casinonmtpbackend.services

import casino.nmtp.web.casinonmtpbackend.entities.User
import casino.nmtp.web.casinonmtpbackend.enums.UserRole
import casino.nmtp.web.casinonmtpbackend.models.requests.UserAuthorizationRequest
import casino.nmtp.web.casinonmtpbackend.models.requests.UserRegisterRequest
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
    fun userAuthorization(userAuthorizationRequest: UserAuthorizationRequest): ResponseEntity<UserAuthorizationResponse> {
        val user = userRepository.authorization(userAuthorizationRequest.login, userAuthorizationRequest.password)
        return if (user != null) {
            ResponseEntity(UserAuthorizationResponse(user.id), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
    }

    fun userRegistration(userRegisterRequest: UserRegisterRequest): ResponseEntity<UserAuthorizationResponse> {
        val user = User(
            login = userRegisterRequest.login,
            password = userRegisterRequest.password,
            username = userRegisterRequest.username,
            role = UserRole.USER,
            registrationDate = LocalDate.now()
        )
        userRepository.save(user)
        return ResponseEntity(UserAuthorizationResponse(user.id), HttpStatus.CREATED)
    }
}