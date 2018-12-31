package com.kolayout.aeee.utils.file

class ManagerFile : ManagerNode{

    var counter: Counter? = null

    override fun _init(folderPathConfig: FolderPathConfig, depth: Int) {
        val maxCounter = folderPathConfig.getFolderCountMax(depth)
        counter = CounterFile(maxCounter)
    }

    override fun isFull(): Boolean { return counter!!.haveToUdateState() }

    override fun _getPath(): String { return "" }
}