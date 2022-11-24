package mx.com.sfinx.secure_module.monitor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecureModuleMonitorApplication

fun main(args: Array<String>) {
	runApplication<SecureModuleMonitorApplication>(*args)
}
