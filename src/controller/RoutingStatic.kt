package pl.pjpsoft.controller

import io.ktor.http.content.*
import io.ktor.routing.*
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