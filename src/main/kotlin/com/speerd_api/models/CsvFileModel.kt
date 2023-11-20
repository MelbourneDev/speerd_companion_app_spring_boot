package com.speerd_api.models

import javax.persistence.*

@Entity
@Table(name = "csvfilemodel")
class CsvFileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CsvFileModelID")
    var csvFileModelID: Int? = null

    @Lob
    @Column(name = "FileName", columnDefinition = "LONGTEXT")
    var fileName: String? = null

    @Lob
    @Column(name = "FileData")
    var fileData: ByteArray? = null

    @Lob
    @Column(name = "UserId", columnDefinition = "LONGTEXT")
    var userId: String? = null
}

