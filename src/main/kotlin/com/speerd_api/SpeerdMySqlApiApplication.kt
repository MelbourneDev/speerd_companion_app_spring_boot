package com.speerd_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpeerdMySqlApiApplication

fun main(args: Array<String>) {
	runApplication<SpeerdMySqlApiApplication>(*args)
}
