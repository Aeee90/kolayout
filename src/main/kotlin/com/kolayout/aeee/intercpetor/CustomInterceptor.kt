package com.kolayout.aeee.intercpetor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomInterceptor: HandlerInterceptorAdapter(){

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger?.debug("Custorm Intercetor.")
        return true;
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
    }
}