package casino.nmtp.web.casinonmtpbackend.repositories

import casino.nmtp.web.casinonmtpbackend.entities.Leaderboard
import casino.nmtp.web.casinonmtpbackend.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.login = :login AND u.password = :password")
    fun authorization(
        @Param("login") login: String,
        @Param("password") password: String,
    ): User?

    @Query("SELECT u FROM User u WHERE u.login = :login")
    fun findByLogin(
        @Param("login") login: String,
    ): User?

    @Modifying
    @Query("DELETE FROM Transaction t WHERE t.user.id = :userId")
    fun deleteUserTransactions(
        @Param("userId") userId: Long,
    )

    @Modifying
    @Query("DELETE FROM User u WHERE u.login = :login")
    fun deleteUser(
        @Param("login") login: String,
    )

    @Query(
        """
        SELECT u.login, u.balance
        FROM User u 
        ORDER BY u.balance DESC LIMIT 10
        """,
    )
    fun getLeaderboard(): List<Leaderboard>

    @Query(
        """
        SELECT COUNT(u) + 1 
        FROM User u 
        WHERE u.balance > (
            SELECT u2.balance 
            FROM User u2 
            WHERE u2.login = :login
        )
        """,
    )
    fun getUserPosition(
        @Param("login") login: String,
    ): Long?
}
