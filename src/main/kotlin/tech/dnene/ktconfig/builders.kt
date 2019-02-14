package tech.dnene.ktconfig

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import arrow.instances.either.monad.binding
import java.io.File

interface Builder<T> {
    fun build(): Either<String, T>
}

fun config(block: ConfigBuilder.() -> ConfigBuilder) =
        ConfigBuilder().apply { block() }

class ConfigBuilder: Builder<Config> {
    var serverConfigBuilder: ServerConfigBuilder? = null
    var pathConfigBuilder: PathConfigBuilder? = null
    override fun build(): Either<String, Config> =
        serverConfigBuilder?.let { scBuilder ->
            pathConfigBuilder?.let { pcBuilder ->
                binding<String, Config> {
                    val serverConfig = scBuilder.build().bind()
                    val pathConfig = pcBuilder.build().bind()
                    Config(serverConfig, pathConfig)
                }
            } ?: "path configuration must be specified".left()
        } ?: "server configuration must be specified".left()
    fun server(block: ServerConfigBuilder.() -> Unit) =
            apply { serverConfigBuilder = ServerConfigBuilder().apply { block() } }

    fun path(block: PathConfigBuilder.() -> Unit) =
            apply { pathConfigBuilder = PathConfigBuilder().apply { block() } }
}

class ServerConfigBuilder: Builder<ServerConfig> {
    var host: String? = null
    var port: Int? = null
    override fun build(): Either<String, ServerConfig> =
        host?.let { host ->
            port?.let { port ->
                ServerConfig(host, port).right()
            } ?: "port must be specified".left()
        } ?: "host must be specified".left()
}

class PathConfigBuilder: Builder<PathConfig> {
    var tmpDirPath: String? = null
    override fun build(): Either<String, PathConfig> =
        tmpDirPath?.let { tmpDirPath ->
            val file = File(tmpDirPath)
            if (file.exists() && file.isDirectory && file.canWrite()) {
                PathConfig(file.toPath()).right()
            } else {
                "tmpDirPath must be a writeable directory".left()
            }
        } ?: "tmpDirPath must be specified".left()
}