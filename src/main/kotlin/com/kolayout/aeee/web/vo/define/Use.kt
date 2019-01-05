package com.kolayout.aeee.web.vo.define

enum class Use(
        val id: String,
        val naming: String
){
    Y("Y", "Y"),
    N("N", "N");

    fun getUse(value: String): Use? {

        return Use.values().find {
            it.id == value || it.naming == value
        }
    }
}