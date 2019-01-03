package com.kolayout.aeee.web.controller

import com.kolayout.aeee.exception.AjaxException
import com.kolayout.aeee.exception.IncorrectParameterException
import com.kolayout.aeee.utils.message.MessageManager
import com.kolayout.aeee.web.service.HomeService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartHttpServletRequest


@RestController
@RequestMapping("/home")
class HomeController{

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var homeService: HomeService

    @Autowired
    private lateinit var messageManager: MessageManager

    @GetMapping("/greeting")
    fun getGreeting(): Map<String, Any>{
        return mapOf(Pair("status", 200), Pair("message", "Hello, This is Layout with the Kotlin!"))
    }

    @PostMapping("/upload")
    fun upload(mreq: MultipartHttpServletRequest): Map<String, Any>{
        try {
            val mfile = mreq.getFile("file")
            if(mfile == null) {
                logger.info("File is necessary.")
                throw IncorrectParameterException()
            }else{
                homeService.upload(mfile)
            }

            return mapOf(Pair("status", 200), Pair(messageManager.MESSAGE_KEY, messageManager.getMessage("upload_success")))
        } catch(e: IncorrectParameterException){ throw AjaxException(e.message!!, e.cause!!, e.getStatus())
        } catch (e: Exception){ throw AjaxException(e.message!!, e.cause!!) }
    }
}