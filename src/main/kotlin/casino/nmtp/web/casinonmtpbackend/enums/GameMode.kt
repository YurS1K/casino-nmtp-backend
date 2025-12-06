package casino.nmtp.web.casinonmtpbackend.enums

enum class GameMode {
    ROCKET,
    JACK,
    MINESWEEPER,
}

fun String.toGameMode(): GameMode =
    when (this) {
        "ROCKET" -> GameMode.ROCKET
        "JACK" -> GameMode.JACK
        "MINESWEEPER" -> GameMode.MINESWEEPER
        else -> GameMode.ROCKET
    }