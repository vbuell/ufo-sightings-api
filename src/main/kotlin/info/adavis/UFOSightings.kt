package info.adavis

import com.google.gson.Gson
import info.adavis.di.mainModule
import info.adavis.graphql.AppSchema
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.TextContent
import io.ktor.http.content.default
import io.ktor.http.content.static
import io.ktor.http.withCharset
import io.ktor.locations.Locations
import io.ktor.response.respond
import io.ktor.routing.routing
import org.koin.ktor.ext.inject
import org.koin.standalone.StandAloneContext

@Suppress("unused")
fun Application.UFOSightings() {
    StandAloneContext.startKoin(listOf(mainModule))

    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respond(
                    TextContent(
                            "${it.value} ${it.description}",
                            ContentType.Text.Plain.withCharset(Charsets.UTF_8),
                            it))
        }
        exception<Throwable> { _ ->
            call.respond(HttpStatusCode.InternalServerError)
        }
    }
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        val appSchema: AppSchema by inject()
        val gson: Gson by inject()

        graphql(log, gson, appSchema.schema)

        static("/") {
            default("index.html")
        }
    }

    log.info("Application setup complete")
}
