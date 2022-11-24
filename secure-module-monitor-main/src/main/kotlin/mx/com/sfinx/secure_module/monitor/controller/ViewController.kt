package mx.com.sfinx.secure_module.monitor.controller

import mx.com.sfinx.secure_module.monitor.business.ApiFiles
import mx.com.sfinx.secure_module.monitor.service.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
@CrossOrigin
class ViewController @Autowired constructor(
    private val apiFiles: ApiFiles,
    private val securityService: SecurityService
){

    @GetMapping("/", "/index")
    fun index(model: Model): String = "index"

    @GetMapping("/login")
    fun login(): String =
        if(securityService.isAuthenticated()) "redirect:/"
        else "login"


    @GetMapping("sam/handled")
    fun handled(): String = "sam/handled"

    @GetMapping("sam/uses")
    fun uses(): String = "sam/uses"

    @GetMapping("sam/failed")
    fun failed(): String = "sam/failed"

    @GetMapping("sam/times")
    fun times(): String = "sam/times"

    @GetMapping("sam/slowness")
    fun slowness(): String = "sam/slowness"

    @GetMapping("sam/status")
    fun samStatus(model: Model): String {

        model.addAttribute("samList", apiFiles.getFiles())

        return "sam/status"
    }
}