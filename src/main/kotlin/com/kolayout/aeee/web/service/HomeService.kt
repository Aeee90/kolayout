package com.kolayout.aeee.web.service

import com.kolayout.aeee.utils.file.FileInfo
import com.kolayout.aeee.utils.file.FileUtils
import com.kolayout.aeee.utils.file.FolderPathConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class HomeService {

    val folderPathConfig = FolderPathConfig.HOME

    @Autowired
    private var fileUtils: FileUtils? = null

    fun upload(mfile: MultipartFile){
        val fileInfo: FileInfo =  fileUtils!!.write(folderPathConfig, mfile)


    }
}