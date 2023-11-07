package com.gifs.app.user.util

import java.io.InputStreamReader

object Helper {
    fun readFileResource(fileName: String): String {
        val inputStream = Helper::class.java.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, Charsets.UTF_8)
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }

    fun returnError(): String {
        return "{\n" +
                "    \"data\": [],\n" +
                "    \"meta\": {\n" +
                "        \"status\": 401,\n" +
                "        \"msg\": \"Unauthorized\",\n" +
                "        \"response_id\": \"\"\n" +
                "    }\n" +
                "}"
    }
}