package com.kolayout.aeee.web

import com.kolayout.aeee.exception.AjaxException
import java.io.IOException
import javax.servlet.http.HttpServletResponse
import com.kolayout.aeee.exception.IncorrectParameterException
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest
import com.kolayout.aeee.utils.message.MessageManager
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionhandler {

    val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private val messageManager: MessageManager? = null


    @ExceptionHandler(value = [AjaxException::class])
    @Throws(IOException::class)
    fun ajaxExceptjionHandler(response: HttpServletResponse, e: AjaxException) {
        logger.debug("Error code: {}", response.status)
        logger.debug("Error message: {}", e.message)

        response.sendError(e.getStatus(), e.message)
    }

    @ExceptionHandler(value = [IncorrectParameterException::class])
    @Throws(IOException::class)
    fun incorrentParameterException(response: HttpServletResponse, e: IncorrectParameterException) {
        logger.debug("Error code: {}", response.status)
        logger.debug("Error message: {}", e.message)

        response.sendError(412, e.message)
    }

}