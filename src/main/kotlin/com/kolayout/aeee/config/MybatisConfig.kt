package com.kolayout.aeee.config

import org.apache.commons.dbcp.BasicDataSource
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.type.JdbcType
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@MapperScan("com.kalayout.aeee.web.mapper")
@PropertySource("classpath:application-\${spring.profiles.active}.properties")
class MybatisConfig {

    @Autowired
    private lateinit var env: Environment

    @Bean
    fun getDataSource(): DataSource {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = env.getProperty("datasource.driverClassName")
        dataSource.url = env.getProperty("datasource.url")
        dataSource.username = env.getProperty("datasource.username")
        dataSource.password = env.getProperty("datasource.password")
        return dataSource
    }

    @Bean
    fun transactionManager(): DataSourceTransactionManager {
        return DataSourceTransactionManager(getDataSource())
    }

    @Bean
    @Throws(Exception::class)
    fun sqlSessionFactory(): SqlSessionFactory? {
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(getDataSource())
        updatessionConfiguration(sessionFactory.getObject()!!.configuration)

        return sessionFactory.getObject()
    }

    private fun updatessionConfiguration(config: org.apache.ibatis.session.Configuration): org.apache.ibatis.session.Configuration {
        //override config
        config.jdbcTypeForNull = JdbcType.NULL
        config.isCallSettersOnNulls = true

        return config
    }
}