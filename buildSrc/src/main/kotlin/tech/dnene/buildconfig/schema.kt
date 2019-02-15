package tech.dnene.buildconfig

import arrow.core.Option
import java.nio.file.Path

data class Config(
    val server: ServerConfig,
    val path: PathConfig)

data class ServerConfig(
    val host: String,
    val port: Option<Int>)

data class PathConfig(
    val tmpDirPath : Path)