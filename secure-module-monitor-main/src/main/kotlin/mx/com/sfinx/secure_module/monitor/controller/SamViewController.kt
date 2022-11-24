package mx.com.sfinx.secure_module.monitor.controller

import mx.com.sfinx.secure_module.monitor.business.ApiFiles
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sam")
@CrossOrigin
class SamViewController constructor(private val apiFiles: ApiFiles){

    @GetMapping("/file-list")
    fun fileList() = ResponseEntity.ok(apiFiles.getFiles())
}