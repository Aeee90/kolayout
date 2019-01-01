package com.kolayout.aeee.exception

import java.lang.Exception

open class ExceptionWithStatus(
        message: String,
        errorCode: Int,
        cause: Throwable? = null
): Exception(message, cause){

    private val status: Int

    init {
        status = errorCode
    }

    fun getStatus() = status

}