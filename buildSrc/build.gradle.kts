plugins {
    kotlin("jvm") version "1.3.21"
    `maven-publish`
}

group = "tech.dnene"
version = "1.0.0-SNAPSHOT"
val arrowVersion = "0.8.2"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("script-runtime"))
    compile(kotlin("compiler-embeddable"))
    compile("io.arrow-kt", "arrow-instances-core", arrowVersion)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = "ktconfig-gradle-support"
            version = project.version.toString()
        }
    }
}