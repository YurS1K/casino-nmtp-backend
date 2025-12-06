package casino.nmtp.web.casinonmtpbackend.controllers

import casino.nmtp.web.casinonmtpbackend.models.requests.UserAuthorizationRequest
import casino.nmtp.web.casinonmtpbackend.models.requests.UserRegisterRequest
import casino.nmtp.web.casinonmtpbackend.models.responses.UserAuthorizationResponse
import casino.nmtp.web.casinonmtpbackend.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (
    private val userService: UserService
) {
    @PostMapping("/auth")
    fun authentification(
        @RequestBody(required = true) userAuthorizationRequest: UserAuthorizationRequest
    ): ResponseEntity<UserAuthorizationResponse> = userService.userAuthorization(userAuthorizationRequest)


    @PostMapping("/register")
    fun register(
        @RequestBody(required = true) userRegisterRequest: UserRegisterRequest
    ): ResponseEntity<UserAuthorizationResponse> = userService.userRegistration(userRegisterRequest)
}