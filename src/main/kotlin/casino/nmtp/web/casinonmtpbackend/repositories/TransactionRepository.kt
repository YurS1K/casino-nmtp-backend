package casino.nmtp.web.casinonmtpbackend.repositories

import casino.nmtp.web.casinonmtpbackend.entities.GameHistory
import casino.nmtp.web.casinonmtpbackend.entities.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {
    @Query(
        """
        SELECT t.date, t.winLostAmount FROM Transaction t
        WHERE t.user.id = :userId AND t.game.gameMode = :gameMode
        ORDER by t.date DESC LIMIT 10
    """,
    )
    fun getLastGames(
        @Param("userId") userId: Long,
        @Param("gameMode") gameMode: String,
    ): List<GameHistory>?

    @Query(
        """
        SELECT COUNT(*) FROM Transaction t 
        WHERE t.user.id = :userId 
        AND (:gameMode is null OR t.game.gameMode = :gameMode) """,
    )
    fun getGameCount(
        @Param("userId") userId: Long,
        @Param("gameMode") gameMode: String?,
    ): Long?

    @Query(
        """
            SELECT SUM(t.winLostAmount) FROM Transaction t 
            WHERE t.user.id = :userId AND (:gameMode is null OR t.game.gameMode = :gameMode) 
            AND t.winLostAmount > 0
        """,
    )
    fun getWinAmout(
        @Param("userId") userId: Long,
        @Param("gameMode") gameMode: String?,
    ): Long?

    @Query(
        """
            SELECT SUM(t.winLostAmount) FROM Transaction t 
            WHERE t.user.id = :userId 
            AND (:gameMode is null OR t.game.gameMode = :gameMode) AND t.winLostAmount < 0
        """,
    )
    fun getLostAmount(
        @Param("userId") userId: Long,
        @Param("gameMode") gameMode: String?,
    ): Long?

    @Query(
        """
        SELECT g.gameMode
        FROM Transaction t
        JOIN Game g ON t.game.id = g.id
        WHERE t.user.id = :userId
        GROUP BY g.id, g.gameMode
        ORDER BY COUNT(t.id) DESC
        LIMIT 1
        """,
    )
    fun getFavouriteGame(
        @Param("userId") userId: Long,
    ): String?
}
