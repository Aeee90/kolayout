package com.kolayout.aeee.utils.file

enum class FolderPathConfig (
        val KIND_PATH: String,
        val FIRST_FOLDER_MAX: Int,
        val FILE_MAX: Int
){

    DISCARD("DISCARD_REQ", 100, 100),
    DISTRIBUTION("DIST", 100, 100),
    DONOR("DONOR", 100, 1000),
    PROJECT("PROJECT", 100, 100),
    SOP("SOP", 100, 1000),
    NOTICE("NOTICE", 100, 1000);


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