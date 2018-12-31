package com.kolayout.aeee.utils.file

interface ManagerNode {
    fun _init(folderPathConfig: FolderPathConfig, depth: Int)
    fun isFull(): Boolean
    fun _getPath(): String
}