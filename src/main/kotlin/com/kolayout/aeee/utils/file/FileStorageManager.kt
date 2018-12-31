package com.kolayout.aeee.utils.file

import org.springframework.stereotype.Component
import java.util.HashMap

@Component
class FileStorageManager {

    private val folderCountMap: MutableMap<FolderPathConfig, ManagerFolder>

    init {
        folderCountMap = HashMap()

        //Set counts
        for (folderConfig in FolderPathConfig.values()) {
            val folderCounter = ManagerFolder()
            folderCounter.init(folderConfig)
            folderCountMap[folderConfig] = folderCounter
        }
    }

    fun getFolderPath(folderConfig: FolderPathConfig): String {
        return folderCountMap[folderConfig]?.getPath()!!
    }

}