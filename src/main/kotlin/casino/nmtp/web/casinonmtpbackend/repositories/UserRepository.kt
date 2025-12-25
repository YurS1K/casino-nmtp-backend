package casino.nmtp.web.casinonmtpbackend.repositories

import casino.nmtp.web.casinonmtpbackend.entities.User
import org.springframework.data.jpa.repository.JpaRepository
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
}
