package mx.com.sfinx.secure_module.monitor.business

import mx.com.sfinx.secure_module.monitor.service.impl.ReadFileServiceImpl
import org.springframework.stereotype.Service

@Service
class ApiFiles constructor(private val readFileServiceImpl: ReadFileServiceImpl){

    fun getFiles() = readFileServiceImpl.getFilesData()
}