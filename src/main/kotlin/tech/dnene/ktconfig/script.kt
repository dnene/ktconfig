package tech.dnene.ktconfig

val configResultKt = config {
    server {
        host = "localhost"
        port = 8080
    }

    path {
        tmpDirPath = "/tmp"
    }
}.build()