import arrow.core.some
import tech.dnene.buildconfig.*

plugins {
    kotlin("jvm") version "1.3.21"
    `maven-publish`
}


group = "tech.dnene"
version = "1.0-SNAPSHOT"

val arrowVersion = "0.8.2"
val config = Config(ServerConfig("localhost", 8080.some()), PathConfig(File("/tmp").toPath()))

repositories {
    mavenCentral()
    jcenter()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("script-runtime"))
    implementation(kotlin("compiler-embeddable"))
    implementation(kotlin("script-util"))
    compile("io.arrow-kt", "arrow-instances-core", arrowVersion)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = project.name
            version = project.version.toString()
        }
    }
}