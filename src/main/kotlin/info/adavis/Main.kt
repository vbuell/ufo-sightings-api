package info.adavis

import info.adavis.dao.UFOSightingsImporter
import info.adavis.di.mainModule
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.Koin
import org.koin.log.PrintLogger
import org.koin.standalone.StandAloneContext.startKoin

fun main(args: Array<String>) {
    setupDI()
    importData()
    startKtor(args)
}

fun importData() {
    UFOSightingsImporter().import()
}

fun startKtor(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

fun setupDI() {
    Koin.logger = PrintLogger()
    startKoin(listOf(mainModule))
}
