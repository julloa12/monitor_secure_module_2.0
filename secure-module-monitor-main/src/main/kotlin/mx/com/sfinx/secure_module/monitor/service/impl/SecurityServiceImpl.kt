package mx.com.sfinx.secure_module.monitor.service.impl

import mx.com.sfinx.secure_module.monitor.service.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityServiceImpl @Autowired constructor(): SecurityService{
    override fun isAuthenticated(): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication

        if(AnonymousAuthenticationToken::class.java.isAssignableFrom(authentication.javaClass))
            return false

        return authentication.isAuthenticated
    }
}