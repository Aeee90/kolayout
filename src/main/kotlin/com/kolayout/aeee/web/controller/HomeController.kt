package com.kolayout.aeee.web.controller

import com.kolayout.aeee.exception.AjaxException
import com.kolayout.aeee.exception.IncorrectParameterException
import com.kolayout.aeee.utils.message.MessageManager
import com.kolayout.aeee.web.service.HomeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartHttpServletRequest


@RestController
@RequestMapping("/home")
class HomeController{

    @Autowired
    private var homeService: HomeService? = null

    @Autowired
    private var messageManager: MessageManager? = null

    @GetMapping("/greeting")
    fun getGreeting(): Map<String, Any>{
        return mapOf(Pair("status", 200), Pair("message", "Hello, This is Layout with the Kotlin!"))
    }

    @PostMapping("/upload")
    fun upload(mreq: MultipartHttpServletRequest): Map<String, Any>{
        try {
            val mfile = mreq.getFile("file")
            if(mfile == null) {

                throw IncorrectParameterException()
            }else{
                homeService!!.upload(mfile!!)
            }

            return mapOf(Pair("status", 200), Pair(messageManager!!.MESSAGE_KEY, messageManager!!.getMessage("upload_success")))
        } catch(e: IncorrectParameterException){ throw AjaxException(e.message!!, e.cause!!, e.getStatus())
        } catch (e: Exception){ throw AjaxException(e.message!!, e.cause!!) }
    }
}