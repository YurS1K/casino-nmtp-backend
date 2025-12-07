package casino.nmtp.web.casinonmtpbackend.services

import casino.nmtp.web.casinonmtpbackend.entities.Game
import casino.nmtp.web.casinonmtpbackend.entities.Transaction
import casino.nmtp.web.casinonmtpbackend.models.requests.GameRegistrationRequest
import casino.nmtp.web.casinonmtpbackend.models.responses.GameRegistrationResponse
import casino.nmtp.web.casinonmtpbackend.repositories.GameRepository
import casino.nmtp.web.casinonmtpbackend.repositories.TransactionRepository
import casino.nmtp.web.casinonmtpbackend.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class GameService (
    val userRepository: UserRepository,
    val gameRepository: GameRepository,
    val transactionRepository: TransactionRepository,
) {
    fun registerGame(gameResult: GameRegistrationRequest): ResponseEntity<GameRegistrationResponse>{
        val game = Game(gameMode = gameResult.game)


        var user = userRepository.findByUsername(gameResult.login) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        user = user.apply { balance += gameResult.winLostAmount}

        val transaction = Transaction(
            game = game,
            user = user,
            winAmount = if (gameResult.winLostAmount > 0) gameResult.winLostAmount else 0,
            lossAmount = if (gameResult.winLostAmount < 0) gameResult.winLostAmount else 0,
            betAmount = gameResult.betAmount
        )

        gameRepository.save(game)
        userRepository.save(user)
        transactionRepository.save(transaction)

        return ResponseEntity(HttpStatus.CREATED)
    }
}
