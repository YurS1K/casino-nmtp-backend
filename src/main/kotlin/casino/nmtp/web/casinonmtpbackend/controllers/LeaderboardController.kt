package casino.nmtp.web.casinonmtpbackend.controllers

import casino.nmtp.web.casinonmtpbackend.models.responses.LeaderboardPositionResponse
import casino.nmtp.web.casinonmtpbackend.models.responses.LeaderboardTopResponse
import casino.nmtp.web.casinonmtpbackend.services.LeaderboardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class LeaderboardController(
    private val leaderboardService: LeaderboardService,
) {
    @GetMapping("/leaderboard")
    fun getLeaderboard(): ResponseEntity<LeaderboardTopResponse> = leaderboardService.getLeaderboard()

    @GetMapping("/leaderboard/{username}")
    fun getUserLeaderboardPosition(
        @PathVariable username: String,
    ): ResponseEntity<LeaderboardPositionResponse> = leaderboardService.getUserLeaderboardPosition(username)
}
