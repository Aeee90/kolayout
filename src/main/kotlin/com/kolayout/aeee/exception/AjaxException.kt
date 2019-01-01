package com.kolayout.aeee.exception

import java.lang.Exception

class AjaxException(
        message: String,
        cause: Throwable,
        errorCode: Int = 500
): ExceptionWithStatus(message, errorCode, cause)