package com.kolayout.aeee.utils.file

import org.slf4j.LoggerFactory
import java.io.File
import java.util.*


class ManagerFolder : ManagerNode{

    private val logger = LoggerFactory.getLogger(this::class.java)

    var NAME: String? = null
    var ROOT_FOLDER: String? = null
    var child: ManagerNode? = null
    var counter: Counter? = null
    var depth: Int = 0

    fun init(folderPathConfig: FolderPathConfig){
        ROOT_FOLDER = folderPathConfig.KIND_PATH
        _init(folderPathConfig, 0)
    }

    override fun _init(folderPathConfig: FolderPathConfig, depth: Int){
        this.depth =depth
        NAME = createName()

        val maxCounter = folderPathConfig.getFolderCountMax(depth)
        counter = CounterFile(maxCounter)
        child = if(maxCounter < 0) ManagerFolder() else ManagerFile()

        child!!._init(folderPathConfig, depth + 1)
    }

    private fun createName(): String{
        return Math.abs(Objects.hashCode(Calendar.getInstance().timeInMillis)).toString() + depth.toString()
    }

    override fun isFull(): Boolean {
        return counter!!.haveToUdateState()
    }

    fun getPath() : String{
        return ROOT_FOLDER + _getPath()
    }

    override fun _getPath(): String {
        if(child!!.isFull()) NAME = createName()
        return File.pathSeparator + NAME + File.pathSeparator + child!!._getPath()
    }
}