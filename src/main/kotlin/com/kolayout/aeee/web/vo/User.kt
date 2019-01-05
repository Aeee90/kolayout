package com.kolayout.aeee.web.vo

import com.kolayout.aeee.web.vo.define.Use
import java.util.*

data class User(
        val id: String
){
    private val pass: String? = null
    private val user_name: String? = null
    private val email: String? = null
    private val mobile: String? = null
    private val organization: String? = null
    private var approved_yn: Use? = null
    private val createTS: Date? = null
    private val approvedTS: Date? = null
    private var delete_yn: Use? = null
    private val delete_ts: Date? = null
}