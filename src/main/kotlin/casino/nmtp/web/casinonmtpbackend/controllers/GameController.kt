package casino.nmtp.web.casinonmtpbackend.controllers

import casino.nmtp.web.casinonmtpbackend.models.requests.GameRegistrationRequest
import casino.nmtp.web.casinonmtpbackend.models.responses.MessageResponse
import casino.nmtp.web.casinonmtpbackend.services.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController(
    private val gameService: GameService,
) {
    @PostMapping("/game")
    fun gameResult(
        @RequestBody gameResult: GameRegistrationRequest,
    ): ResponseEntity<MessageResponse> = gameService.registerGame(gameResult)
}
