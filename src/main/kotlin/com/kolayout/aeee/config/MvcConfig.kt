package com.kolayout.aeee.config

import com.kolayout.aeee.intercpetor.CustomInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = arrayOf("com.kolayout.aeee"))
class MvcConfig : WebMvcConfigurer{

    @Autowired
    private val env: Environment? = null

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(CustomInterceptor())
    }

    @Bean
    fun getJavaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = env?.getProperty("mail.host")
        mailSender.port = Integer.valueOf(env?.getProperty("mail.port")!!)

        mailSender.username = env.getProperty("mail.username")
        mailSender.password = env.getProperty("mail.password")

        val props = mailSender.javaMailProperties
        props["mail.password"] = env.getProperty("mail.password")
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.ssl.enable"] = "true"
        props["mail.debug"] = "true"

        return mailSender
    }

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val localeChangeInterceptor = LocaleChangeInterceptor()
        localeChangeInterceptor.paramName = "language"
        return localeChangeInterceptor
    }

    @Bean
    fun sessionLocaleResolver(): LocaleResolver {
        val localeResolver = SessionLocaleResolver()
        localeResolver.setDefaultLocale(Locale("ko_KO"))
        return localeResolver
    }
}