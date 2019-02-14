package tech.dnene.ktconfig

import arrow.core.Try
import arrow.core.getOrDefault
import arrow.core.getOrElse
import arrow.core.left
import java.io.File
import java.io.FileReader
import javax.script.ScriptEngineManager

fun main(args: Array<String>) {
    configResultKt.fold(
        { println("Could not load kotlin configuration successfully. Error: ${it}")},
        { println("Loaded kotlin configuration successfully: ${it}")})

    val configResultKts =
        Try {
            ScriptEngineManager(Thread.currentThread().contextClassLoader)
                .getEngineByExtension("kts")
                .eval(FileReader(File("src/main/config/sample.kts")))
                .let { (it as? ConfigBuilder)?.build() }
                ?: "Could not find/load/compile script".left()
        }.getOrElse { "Exception when running kts script ${it.message}".left() }

    configResultKts.fold(
        { println("Could not load kts configuration successfully. Error: ${it}")},
        { println("Loaded kts configuration successfully: ${it}")})
}