package casino.nmtp.web.casinonmtpbackend.services

import casino.nmtp.web.casinonmtpbackend.entities.Game
import casino.nmtp.web.casinonmtpbackend.entities.Transaction
import casino.nmtp.web.casinonmtpbackend.models.requests.GameRegistrationRequest
import casino.nmtp.web.casinonmtpbackend.models.responses.MessageResponse
import casino.nmtp.web.casinonmtpbackend.repositories.GameRepository
import casino.nmtp.web.casinonmtpbackend.repositories.TransactionRepository
import casino.nmtp.web.casinonmtpbackend.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class GameService(
    val userRepository: UserRepository,
    val gameRepository: GameRepository,
    val transactionRepository: TransactionRepository,
) {
    fun registerGame(gameResult: GameRegistrationRequest): ResponseEntity<Any> {
        val game = Game(gameMode = gameResult.game)

        var user =
            userRepository.findByLogin(gameResult.login)
                ?: return ResponseEntity(
                    MessageResponse("Пользователь не найден"),
                    HttpStatus.NOT_FOUND,
                )
        user = user.apply { balance += gameResult.winLostAmount }

        if (user.balance <= 0) {
            user.balance = 0
            user.freezeTime = LocalDateTime.now().plusSeconds(5)
        }

        val transaction =
            Transaction(
                game = game,
                user = user,
                winLostAmount = gameResult.winLostAmount,
                betAmount = gameResult.betAmount,
            )

        gameRepository.save(game)
        userRepository.save(user)
        transactionRepository.save(transaction)

        return ResponseEntity(HttpStatus.OK)
    }
}
