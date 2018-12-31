package com.kolayout.aeee.utils.file

open class CounterFolder(
        override val maxCounter:Int
): Counter{

    override var curCounter: Int = 0

    override fun haveToUdateState(): Boolean {
        return when (maxCounter) {
            0 -> false
            else -> {
                if (++curCounter >= maxCounter) {
                    curCounter = 0
                    true
                }else {
                    false
                }
            }
        }
    }
}