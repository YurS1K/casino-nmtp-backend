package casino.nmtp.web.casinonmtpbackend.repositories

import casino.nmtp.web.casinonmtpbackend.entities.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : JpaRepository<Game, Long>