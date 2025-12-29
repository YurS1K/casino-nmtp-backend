package casino.nmtp.web.casinonmtpbackend.services

import casino.nmtp.web.casinonmtpbackend.models.responses.LeaderboardPositionResponse
import casino.nmtp.web.casinonmtpbackend.models.responses.LeaderboardTopResponse
import casino.nmtp.web.casinonmtpbackend.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class LeaderboardService(
    private val userRepository: UserRepository,
) {
    fun getLeaderboard(): ResponseEntity<LeaderboardTopResponse> {
        return ResponseEntity<LeaderboardTopResponse>(LeaderboardTopResponse(userRepository.getLeaderboard()), HttpStatus.OK)
    }

    fun getUserLeaderboardPosition(username: String): ResponseEntity<LeaderboardPositionResponse> {
        val position = userRepository.getUserPosition(username) ?: return ResponseEntity<LeaderboardPositionResponse>(HttpStatus.NOT_FOUND)
        return ResponseEntity<LeaderboardPositionResponse>(LeaderboardPositionResponse(position), HttpStatus.OK)
    }
}
