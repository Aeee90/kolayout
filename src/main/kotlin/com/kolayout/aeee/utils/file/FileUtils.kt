package com.kolayout.aeee.utils.file

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Configuration
@PropertySource("classpath:application-\${spring.profiles.active}.properties")
@Component
class FileUtils {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Value("\${app.upload.dir}")
    private val UPLOAD_PATH: String? = null

    private val fileStorageManager: FileStorageManager = FileStorageManager()

    fun write(folderPath: FolderPathConfig, files: List<MultipartFile>): List<FileInfo> =
        files.map {
            _write(validFolder(folderPath), it)
        }


    fun write(folderPath: FolderPathConfig, file: MultipartFile): FileInfo = _write(validFolder(folderPath), file)


    private fun validFolder(folderPath: FolderPathConfig): String{
        val DIR_PATH = fileStorageManager?.getFolderPath(folderPath)!!
        val FULL_PATH = UPLOAD_PATH + File.separator + DIR_PATH

        val directory = File(FULL_PATH)
        if (!directory.exists()) {
            if (!File(FULL_PATH).mkdirs()) logger.debug("Make {} directory : Fail", FULL_PATH)
        }

        return DIR_PATH
    }

    private fun _write(DIR_PATH: String, file: MultipartFile): FileInfo{

        val FILE_PATH = DIR_PATH + File.separator + getFileNameToHash(file)
        val FILE_NAME = file.originalFilename

        FileOutputStream(File(UPLOAD_PATH + File.separator + FILE_PATH)).use {
            it.write(file.bytes)
        }

        return FileInfo(FILE_NAME, FILE_PATH)
    }


    private fun getFileNameToHash(mf: MultipartFile): String {
        val originName = mf.originalFilename?.toString()?.split(".")
        val name = originName!![0]
        val ext = originName[1]
        return Math.abs(Objects.hashCode(Calendar.getInstance().timeInMillis.toString() + name)).toString() + "." + ext
    }
}
