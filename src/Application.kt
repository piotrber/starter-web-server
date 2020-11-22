package pl.pjpsoft

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.mustache.*
import io.ktor.routing.*
import pl.pjpsoft.controller.firstRouting
import pl.pjpsoft.controller.mainRouting
import pl.pjpsoft.controller.secondRouting

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {


    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates/mustache")
    }
    install(DefaultHeaders)

    routing {
        firstRouting()
        mainRouting()
        secondRouting()
    }
}



