package mx.com.sfinx.secure_module.monitor.model

import java.io.Serializable
import java.sql.Timestamp
import java.util.Date

data class FileModel(
    var samNumber: Int = 0,
    var uidSam: String = "",
    var status: String = "OK",
    var successUses: Int = 0,
    var failedUses: Int = 0,
    var initTime: Long = 0,
    var endTime: Long = 0,
    var authentication: String = "OK",
    var date: String ="", // Timestamp = Timestamp(Date().time),
    var accumulated: Long = 0,
    var MaxT: Int = 0
): Serializable
