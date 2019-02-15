import java.nio.file.Path

data class Config(
    val server: ServerConfig,
    val path: PathConfig)

data class ServerConfig(
    val host: String,
    val port: Int?)

data class PathConfig(
    val tmpDirPath : Path
)

val foo = Config(ServerConfig("localhost", 8080), PathConfig("/tmp"))