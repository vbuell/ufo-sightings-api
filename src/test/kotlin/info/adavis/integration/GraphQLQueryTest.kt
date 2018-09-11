package info.adavis.integration

import info.adavis.UFOSightingsTestApp
import io.ktor.http.*
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import org.junit.Test
import kotlin.test.assertEquals

class GraphQLQueryTest {

    @Test
    fun testRequests() = UFOSightingsTestApp {
        val call = handleRequest(HttpMethod.Post, "/graphql") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody("{query: \"query{topCountrySightings {numOccurrences country}}\", variables: null}")
        }
        with(call) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(
                    "{\n" +
                    "  \"data\" : {\n" +
                    "    \"topCountrySightings\" : [ {\n" +
                    "      \"numOccurrences\" : 8021,\n" +
                    "      \"country\" : \"US\"\n" +
                    "    }, {\n" +
                    "      \"numOccurrences\" : 860,\n" +
                    "      \"country\" : \"\"\n" +
                    "    }, {\n" +
                    "      \"numOccurrences\" : 293,\n" +
                    "      \"country\" : \"CA\"\n" +
                    "    }, {\n" +
                    "      \"numOccurrences\" : 69,\n" +
                    "      \"country\" : \"GB\"\n" +
                    "    }, {\n" +
                    "      \"numOccurrences\" : 46,\n" +
                    "      \"country\" : \"AU\"\n" +
                    "    }, {\n" +
                    "      \"numOccurrences\" : 9,\n" +
                    "      \"country\" : \"DE\"\n" +
                    "    } ]\n" +
                    "  }\n" +
                    "}" +
                    "", response.content)
        }
    }
}
