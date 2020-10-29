package com.github.sourcefranke.dicearithmetic.web

import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WebTest {
    @Test
    fun testRequest() = withTestApplication ({
        mainModule()
    }) {
        with(handleRequest(HttpMethod.Get, "/api/roll/d6")) {
            assertEquals(HttpStatusCode.OK, response.status())

            val content = response.content!!
            assertTrue(content.contains("\"formula\" : \"d6\""))
            assertTrue(content.contains("\"min\" : 1"))
            assertTrue(content.contains("\"max\" : 6"))
        }

        with(handleRequest(HttpMethod.Get, "/api/roll/3+4+5")) {
            assertEquals(HttpStatusCode.OK, response.status())

            val content = response.content!!
            assertTrue(content.contains("\"formula\" : \"3+4+5\""))
            assertTrue(content.contains("\"min\" : 12"))
            assertTrue(content.contains("\"max\" : 12"))
            assertTrue(content.contains("\"results\" : [ 12 ]"))
        }
    }
}