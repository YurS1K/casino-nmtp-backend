package casino.nmtp.web.casinonmtpbackend.controllers

import casino.nmtp.web.casinonmtpbackend.models.requests.UserDeleteRequest
import casino.nmtp.web.casinonmtpbackend.models.requests.UserEditRequest
import casino.nmtp.web.casinonmtpbackend.models.requests.UserLoginPasswordRequest
import casino.nmtp.web.casinonmtpbackend.models.responses.LastGamesResponse
import casino.nmtp.web.casinonmtpbackend.models.responses.StatisticsResponse
import casino.nmtp.web.casinonmtpbackend.models.responses.UserInfoResponse
import casino.nmtp.web.casinonmtpbackend.models.responses.UserLoginResponse
import casino.nmtp.web.casinonmtpbackend.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/auth")
    fun authentification(
        @RequestBody userAuthorizationRequest: UserLoginPasswordRequest,
    ): ResponseEntity<UserLoginResponse> = userService.userAuthorization(userAuthorizationRequest)

    @PostMapping("/register")
    fun register(
        @RequestBody userRegisterRequest: UserLoginPasswordRequest,
    ): ResponseEntity<UserLoginResponse> = userService.userRegistration(userRegisterRequest)

    @PostMapping("/profile/{username}")
    fun userInfo(
        @PathVariable username: String,
    ): ResponseEntity<UserInfoResponse> = userService.getUserInfo(username)

    @PatchMapping("/edit")
    fun editUserProfile(
        @RequestBody userEditRequest: UserEditRequest,
    ): ResponseEntity<Any> = userService.editUserInfo(userEditRequest)

    @DeleteMapping("/delete")
    fun deleteUser(
        @RequestBody userDeleteRequest: UserDeleteRequest,
    ): ResponseEntity<Any> = userService.deleteUser(userDeleteRequest)

    @GetMapping("/last-games/{username}/{gameMode}")
    fun getLastGames(
        @PathVariable username: String,
        @PathVariable gameMode: String,
    ): ResponseEntity<LastGamesResponse> = userService.getLastGames(username, gameMode)

    @GetMapping("/statistics/{username}/{gameMode}")
    fun getUserStatisticsByGame(
        @PathVariable username: String,
        @PathVariable gameMode: String,
    ): ResponseEntity<StatisticsResponse> = userService.getUserStatisticsByGameMode(username, gameMode)

    @GetMapping("/total-statistics/{username}")
    fun getTotalUserStatistics(
        @PathVariable username: String,
    ): ResponseEntity<StatisticsResponse> = userService.getTotalUserStatistics(username)
}
