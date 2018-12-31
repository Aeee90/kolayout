package com.kolayout.aeee.utils.file

import java.io.File

interface Counter {
    val maxCounter: Int
    var curCounter: Int
    fun haveToUdateState(): Boolean
}