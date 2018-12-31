package com.kolayout.aeee.config

import com.kolayout.aeee.filter.LogFilter
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import javax.servlet.Filter
import javax.servlet.ServletContext

@Configuration
class WebConfig: AbstractAnnotationConfigDispatcherServletInitializer(){

    override fun onStartup(servletContext: ServletContext) {
        super.onStartup(servletContext)

        val encodingFilter = servletContext.addFilter("Encoding-filter", CharacterEncodingFilter())
        encodingFilter.addMappingForUrlPatterns(null, false, "/*")
        encodingFilter.setInitParameter("encoding", "UTF-8")
        encodingFilter.setInitParameter("forceEncoding", "true")

        val logFilter = servletContext.addFilter("Log-Filter", LogFilter())
        logFilter.addMappingForUrlPatterns(null, false, "/*")
        logFilter.setInitParameter("forceEncoding", "true")

    }

    override fun getRootConfigClasses(): Array<Class<*>>? {
        return null
    }

    override fun getServletConfigClasses(): Array<Class<*>>? {
        return arrayOf(MvcConfig::class.java)
    }

    override fun getServletMappings(): Array<String> {
        return arrayOf("/*")
    }

    override fun getServletFilters(): Array<Filter>? {
        return arrayOf()
    }
}