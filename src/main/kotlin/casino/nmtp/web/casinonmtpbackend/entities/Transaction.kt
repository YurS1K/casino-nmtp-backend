package casino.nmtp.web.casinonmtpbackend.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0L,

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    var game : Game,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user : User,

    var date: LocalDate = LocalDate.now(),

    var winLostAmount: Long,

    var betAmount: Long,
)
