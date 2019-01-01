package com.kolayout.aeee.utils.message

import java.util.Locale
import org.springframework.context.MessageSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class MessageManager {

    val MESSAGE_KEY = "MESSAGE"

    @Autowired
    private val messageSource: MessageSource? = null

    fun getMessage(code: String): String {
        return messageSource!!.getMessage(code, null, Locale.getDefault())
    }
}