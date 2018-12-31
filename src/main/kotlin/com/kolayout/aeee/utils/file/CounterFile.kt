package com.kolayout.aeee.utils.file

class CounterFile(
        override var maxCounter:Int
): Counter{

    override var curCounter: Int = 0

    override fun haveToUdateState(): Boolean {
        if(++curCounter >= maxCounter){
            curCounter = 0
            return true
        }
        return false
    }
}