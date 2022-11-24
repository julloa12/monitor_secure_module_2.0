package mx.com.sfinx.secure_module.monitor.service.impl

import mx.com.sfinx.secure_module.monitor.model.FileModel
import mx.com.sfinx.secure_module.monitor.service.ReadFileService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.sql.Timestamp
import java.text.SimpleDateFormat

@Service
class ReadFileServiceImpl : ReadFileService {
    //@Value("D:\\proyectos_sfinx\\JAL\\monitor_secure_module_2.0\\secure-module-monitor-main")
    @Value("\${mx.com.sfinx.secure_module.monitor.route-files}")

    private lateinit var route: String

    companion object {
        private val log = LoggerFactory.getLogger(ReadFileServiceImpl::class.java)
        private val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    }

    override fun getFilesData(): List<FileModel> {
        val fileList = File(route).listFiles()

        val list = mutableListOf<FileModel>()
        if (fileList.isNullOrEmpty()) {
            log.warn("The file list is empty")
            return list
        }


        log.info("The file list has {} files", fileList.size)
        for (f in fileList) {
            log.info("File name = {}", f.name)
            if(!f.name.startsWith("SAM"))
                continue

            if(!f.name.endsWith(".log"))
                continue

            val lines = f.useLines { it.toList() }
            log.info("File lines {}.", lines)
            val m = FileModel().apply {
                samNumber = lines[0].split(" ")[2].trim().toInt()
                uidSam = lines[1].split(":")[1].trim()
                status = lines[2].split(":")[1].trim()
                successUses = lines[3].split(":")[1].trim().toIntOrNull() ?: 0
                failedUses = lines[4].split(":")[1].trim().toIntOrNull() ?: 0
                initTime = lines[5].split(":")[1].trim().toLongOrNull() ?: 0
                endTime = lines[6].split(":")[1].trim().toLongOrNull() ?: 0
                authentication = lines[7].split(":")[1].trim()
                val dt = lines[8].split(":")
                val s = dt[1].trim() + ":" + dt[2].trim()+ ":" +  dt[3].trim()
                val seconds= dt[3].split(".")
                val dat=dt[1].split("T")
                System.out.println("--------1------------ "+dt[1].trim())
                System.out.println("---------2----------- "+dt[2].trim())
                System.out.println("----------3---------- "+dt[3].trim())
                date = dat[0]+" "+dat[1]+":"+dt[2].trim()+":"+seconds[0]//Timestamp(sdf.parse(s).time)
                accumulated = lines[9].split(":")[1].trim().toLongOrNull() ?: 0
                MaxT = lines[10].split(":")[1].trim().toIntOrNull() ?: 0
            }
            list.add(m)
        }
        list.sortBy { it.samNumber  }

        return list
    }
}