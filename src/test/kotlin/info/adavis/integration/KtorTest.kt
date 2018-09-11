package info.adavis.integration

import info.adavis.UFOSightingsTestApp
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import org.junit.Test
import org.koin.test.KoinTest
import kotlin.test.assertEquals

class KtorTest : KoinTest {
    @Test
    fun testRoot() {
        UFOSightingsTestApp {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun testNonExistingURL() {
        UFOSightingsTestApp {
            handleRequest(HttpMethod.Get, "/i-dont-exist").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
            }
        }
    }
}
