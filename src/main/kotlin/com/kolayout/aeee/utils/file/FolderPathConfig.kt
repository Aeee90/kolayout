package com.kolayout.aeee.utils.file

enum class FolderPathConfig (
        val KIND_PATH: String,
        val FIRST_FOLDER_MAX: Int,
        val FILE_MAX: Int
){

    HOME("HOME", 100, 100);

    fun getFolderCountMax(depth: Int): Int {
        var max: Int
        when (depth) {
            0 -> max = 0
            1 -> max = FIRST_FOLDER_MAX
            else -> max = -1
        }

        return max
    }

    fun haveNextFolder(depth: Int): Boolean {
        var depth = depth
        return getFolderCountMax(++depth) != 0
    }
}