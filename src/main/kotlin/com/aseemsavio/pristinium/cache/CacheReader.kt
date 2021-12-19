package com.aseemsavio.pristinium.cache

import java.io.File
import java.lang.Exception

class CacheReader(val files: MutableList<File>, var directoryCount: Int = 0, var filesSize: Long = 0) {
    fun listOfFiles(dirPath: File) {
        if (dirPath.exists()) {
            val filesList = dirPath.listFiles()?.filterNotNull()
            if (filesList != null) {
                for (file in filesList) {
                    if (!file.isDirectory && file.isFile) {
                        files += file
                        filesSize += file.length()
                    } else if (file.isDirectory) {
                        directoryCount += 1
                        listOfFiles(file)
                    }
                }
            }
        }
    }
}

fun getAllCacheFiles(): Files {
    val reader = CacheReader(mutableListOf())
    try {
        reader.listOfFiles(File("/Users/aseemsavio/Library/Caches"))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return Files(reader.files.size, reader.directoryCount, reader.filesSize)
}

data class Files(
    val fileCount: Int,
    val directoryCount: Int,
    val totalFileSizesBytes: Long,
) {
    val sizeInKb get() = totalFileSizesBytes / 1024
    val sizeInMb get() = sizeInKb / 1024
    val sizeInGb get() = sizeInMb / 1024
}
