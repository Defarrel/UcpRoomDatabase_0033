package com.example.ucp2.data.entity

import androidx.room.PrimaryKey

data class Dokter(
    @PrimaryKey
    val id: String,
    val nama: String,
    val spesialis: String,
    val klinik: String,
    val noHp: String,
    val jamKerja: String
)
