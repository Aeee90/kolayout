package com.kolayout.aeee.web.mapper

import com.kolayout.aeee.web.vo.Login
import com.kolayout.aeee.web.vo.User

interface UserMapper {

    fun count(parameterMap: Map<String, Any>): Int
    fun list(parameterMap: Map<String, Any>): List<Map<String, Any>>
    fun login(parameterMap: Map<String, Any>): Login
    fun getUser(parameter: Map<String, Any>): User

    fun addUser(parameter: Map<String, Any>): Int

    fun approvedUser(parameterMap: Map<String, Any>): Int
    fun updatePass(parameterMap: Map<String, Any>): Int
    fun updateUser(parameterMap: Map<String, Any>): Int
}