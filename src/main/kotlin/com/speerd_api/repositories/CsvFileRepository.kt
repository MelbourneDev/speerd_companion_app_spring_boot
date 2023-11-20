package com.speerd_api.repositories

import com.speerd_api.models.CsvFileModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CsvFileRepository : JpaRepository<CsvFileModel, Int> {
    fun findByUserId(userId: String): List<CsvFileModel>
}
