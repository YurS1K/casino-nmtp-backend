package casino.nmtp.web.casinonmtpbackend.controllers

import casino.nmtp.web.casinonmtpbackend.models.responses.UserAuthorizationResponse
import casino.nmtp.web.casinonmtpbackend.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (
    private val userService: UserService
) {
    @GetMapping("/auth")
    fun authentification(
        @RequestParam(value = "login", required = true) login: String,
        @RequestParam(value = "password", required = true) password: String,
    ): ResponseEntity<UserAuthorizationResponse> = userService.userAuthorization(login, password)


    @PostMapping("/register")
    fun register(
        @RequestParam(value = "login", required = true) login: String,
        @RequestParam(value = "password", required = true) password: String,
        @RequestParam(value = "username", required = true) username: String,
    ): ResponseEntity<UserAuthorizationResponse> = userService.userRegistration(login, password, username)
}