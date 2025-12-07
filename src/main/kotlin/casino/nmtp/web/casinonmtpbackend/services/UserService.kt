package casino.nmtp.web.casinonmtpbackend.services

import casino.nmtp.web.casinonmtpbackend.entities.User
import casino.nmtp.web.casinonmtpbackend.models.requests.UserAuthorizationRequest
import casino.nmtp.web.casinonmtpbackend.models.requests.UserRegisterRequest
import casino.nmtp.web.casinonmtpbackend.models.responses.UserIdResponse
import casino.nmtp.web.casinonmtpbackend.models.responses.UserInfoResponse
import casino.nmtp.web.casinonmtpbackend.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserService (
    val userRepository: UserRepository
) {
    fun userAuthorization(userAuthorizationRequest: UserAuthorizationRequest): ResponseEntity<UserIdResponse> {
        val user = userRepository.authorization(userAuthorizationRequest.login, userAuthorizationRequest.password)
        return if (user != null) {
            ResponseEntity(UserIdResponse(user.id), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
    }

    fun userRegistration(userRegisterRequest: UserRegisterRequest): ResponseEntity<UserIdResponse> {
        if (userRepository.existsUser(userRegisterRequest.login) != null) return ResponseEntity(HttpStatus.CONFLICT)
        val user = User(
            login = userRegisterRequest.login,
            password = userRegisterRequest.password,
            username = userRegisterRequest.username,
            registrationDate = LocalDate.now()
        )
        userRepository.save(user)
        return ResponseEntity(UserIdResponse(user.id), HttpStatus.CREATED)
    }

    fun getUserInfo(id: Long): ResponseEntity<UserInfoResponse> {
        val user = userRepository.findById(id).get()
        return ResponseEntity(UserInfoResponse(user.username, user.registrationDate,user.balance), HttpStatus.OK)
    }
}