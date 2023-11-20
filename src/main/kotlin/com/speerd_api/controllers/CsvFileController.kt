package com.speerd_api.controllers

import com.speerd_api.models.CsvFileModel
import com.speerd_api.services.CsvFileService
import org.springframework.beans.factory.annotation.Autowired
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/csvfiles")
class CsvFileController(@Autowired private val csvFileService: CsvFileService) {

    private val logger = LoggerFactory.getLogger(CsvFileController::class.java)

    @GetMapping("/user/{userId}")
    fun getFilesByUserId(@PathVariable userId: String): List<CsvFileModel> {
        logger.info("Request to get files by UserId: $userId")

        val files = csvFileService.getFilesByUserId(userId)
        if (files.isEmpty()) {
            logger.info("No files found for UserId: $userId")
        } else {
            logger.info("Found ${files.size} files for UserId: $userId")
            files.forEach { file ->
                logger.info("File: ${file.fileName}, ID: ${file.csvFileModelID}")
            }
        }
        return files
    }

    @DeleteMapping("/{id}")
    fun deleteFile(@PathVariable id: Int): ResponseEntity<Unit> {
        return if (csvFileService.deleteFileById(id)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/download/{id}")
    fun downloadFile(@PathVariable id: Int, response: HttpServletResponse): ResponseEntity<Unit> {
        val csvFile: CsvFileModel? = csvFileService.getFileById(id)
        if (csvFile != null && csvFile.fileData != null) {
            response.contentType = "text/csv"
            response.setHeader("Content-Disposition", "attachment; filename=\"${csvFile.fileName}\"")
            response.outputStream.use { outputStream ->
                outputStream.write(csvFile.fileData)
                outputStream.flush()
            }
            return ResponseEntity.ok().build()
        } else {
            return ResponseEntity.notFound().build()
        }
    }
}









