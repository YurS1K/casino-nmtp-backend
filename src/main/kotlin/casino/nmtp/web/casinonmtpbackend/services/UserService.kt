package casino.nmtp.web.casinonmtpbackend.services

import casino.nmtp.web.casinonmtpbackend.entities.User
import casino.nmtp.web.casinonmtpbackend.models.requests.UserDeleteRequest
import casino.nmtp.web.casinonmtpbackend.models.requests.UserEditRequest
import casino.nmtp.web.casinonmtpbackend.models.requests.UserLoginPasswordRequest
import casino.nmtp.web.casinonmtpbackend.models.responses.UserInfoResponse
import casino.nmtp.web.casinonmtpbackend.models.responses.UserLoginResponse
import casino.nmtp.web.casinonmtpbackend.repositories.TransactionRepository
import casino.nmtp.web.casinonmtpbackend.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserService(
    val userRepository: UserRepository,
    val transactionRepository: TransactionRepository,
) {
    fun userAuthorization(userLoginPasswordRequest: UserLoginPasswordRequest): ResponseEntity<UserLoginResponse> {
        val user = userRepository.authorization(userLoginPasswordRequest.login, userLoginPasswordRequest.password)
        return if (user != null) {
            ResponseEntity(UserLoginResponse(user.login), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
    }

    fun userRegistration(userLoginPasswordRequest: UserLoginPasswordRequest): ResponseEntity<UserLoginResponse> {
        if (userRepository.findByLogin(userLoginPasswordRequest.login) != null) return ResponseEntity(HttpStatus.CONFLICT)
        val user =
            User(
                login = userLoginPasswordRequest.login,
                password = userLoginPasswordRequest.password,
                registrationDate = LocalDate.now(),
            )
        userRepository.save(user)
        return ResponseEntity(UserLoginResponse(user.login), HttpStatus.CREATED)
    }

    fun getUserInfo(login: String): ResponseEntity<UserInfoResponse> {
        val user =
            userRepository.findByLogin(login)
                ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(UserInfoResponse(user.login, user.registrationDate, user.balance), HttpStatus.OK)
    }

    fun editUserInfo(userEditRequest: UserEditRequest): ResponseEntity<Any> {
        val user = userRepository.findByLogin(userEditRequest.login) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        if (userEditRequest.newLogin != null) {
            userRepository.findByLogin(userEditRequest.newLogin)?.let { return ResponseEntity(HttpStatus.CONFLICT) }
        }
        user.login = userEditRequest.newLogin ?: user.login
        user.password = userEditRequest.password ?: user.password

        userRepository.save(user)

        return ResponseEntity(HttpStatus.OK)
    }

    @Transactional
    fun deleteUser(userDeleteRequest: UserDeleteRequest): ResponseEntity<Any> {
        val user = userRepository.findByLogin(userDeleteRequest.login) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        userRepository.deleteUserTransactions(user.id)
        userRepository.deleteUser(user.login)

        return ResponseEntity(HttpStatus.OK)
    }
}
