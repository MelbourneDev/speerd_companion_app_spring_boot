package com.speerd_api.services

import com.speerd_api.models.CsvFileModel
import com.speerd_api.repositories.CsvFileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CsvFileService(@Autowired private val csvFileRepository: CsvFileRepository) {

    fun getFilesByUserId(userId: String): List<CsvFileModel> {
        return csvFileRepository.findByUserId(userId)
    }

    fun deleteFileById(id: Int): Boolean {
        return if (csvFileRepository.existsById(id)) {
            csvFileRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    fun getFileById(id: Int): CsvFileModel? {
        return csvFileRepository.findById(id).orElse(null)
    }
}
