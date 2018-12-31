package com.kolayout.aeee.filter

import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse


class LogFilter: GenericFilterBean(){

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        logger.debug(
            request?.attributeNames?.asSequence()?.map {
                "$it is ${request?.getParameter(it)}"
            }?.joinToString { "/" }
        )

        chain?.doFilter(request, response)
    }
}