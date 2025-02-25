package com.enridev.pomidorki

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PomidorkiApplication

fun main(args: Array<String>) {
	runApplication<PomidorkiApplication>(*args)
}
