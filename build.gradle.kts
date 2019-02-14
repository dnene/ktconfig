plugins {
    kotlin("jvm") version "1.3.21"
}

group = "tech.dnene"
version = "1.0-SNAPSHOT"

val arrowVersion = "0.8.2"

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