package info.adavis

import info.adavis.di.mainModule
import io.ktor.config.MapApplicationConfig
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication
import org.koin.Koin
import org.koin.log.PrintLogger
import org.koin.standalone.StandAloneContext
import org.koin.standalone.StandAloneContext.startKoin

fun UFOSightingsTestApp(callback: TestApplicationEngine.() -> Unit) {
    Koin.logger = PrintLogger()
    startKoin(listOf(mainModule))
    importData()
    withTestApplication({
                            (environment.config as MapApplicationConfig).apply {
                                put("random.param", "03e156f6058a13813816065")
                            }
                            UFOSightings()
                        }, callback)
    StandAloneContext.closeKoin()
}
