package com.kolayout.aeee.web.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/home")
class HomeController{

    @GetMapping("/greeting")
    fun getGreeting(): Map<String, Any>{
        return mapOf(Pair("status", 200), Pair("message", "Hello, This is Layout with the Kotlin!"))
    }
}