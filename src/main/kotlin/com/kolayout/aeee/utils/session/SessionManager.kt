package com.kolayout.aeee.utils.session

import org.springframework.stereotype.Component
import javax.servlet.http.HttpSession

enum class SessionManager {

    USER("USER");

    private val key: String

    private constructor(key: String){
        this.key = key
    }

    fun getKey(): String {
        return key
    };

    fun <Type> extractTypeFrom(session: HttpSession?): Type? {
        if (session != null) {
            val value = session.getAttribute(key)
            if (value != null) return value as Type
        }
        return null
    }
}