package pl.pjpsoft.controller

import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File

fun Routing.staticRouting() {

    static {

        staticRootFolder = File("public")
        static("/") {
            files("css")
            files("js")
        }
    }
}