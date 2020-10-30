package com.github.sourcefranke.dicearithmetic.rest

import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RestTest {
    @Test
    fun testRequest() = withTestApplication ({ restModule() }) {
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

        with(handleRequest(HttpMethod.Get, "/api/roll/5/6+6+6")) {
            assertEquals(HttpStatusCode.OK, response.status())

            val content = response.content!!
            assertTrue(content.contains("\"formula\" : \"6+6+6\""))
            assertTrue(content.contains("\"min\" : 18"))
            assertTrue(content.contains("\"max\" : 18"))
            assertTrue(content.contains("\"results\" : [ 18, 18, 18, 18, 18 ]"))
        }
    }
}