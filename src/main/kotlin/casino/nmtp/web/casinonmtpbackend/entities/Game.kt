package casino.nmtp.web.casinonmtpbackend.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "games")
data class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0L,

    var gameMode: String,
)
