package mx.com.sfinx.secure_module.monitor.service

import mx.com.sfinx.secure_module.monitor.model.FileModel

interface ReadFileService {

    fun getFilesData(): List<FileModel>
}