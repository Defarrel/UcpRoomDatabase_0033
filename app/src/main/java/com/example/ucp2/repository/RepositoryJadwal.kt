package com.example.ucp2.repository

import com.example.ucp2.data.dao.DokterDao
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

interface RepositoryJadwal {
    suspend fun insertJadwal(jadwal: Jadwal)
    suspend fun updateJadwal(jadwal: Jadwal)
    suspend fun deleteJadwal(jadwal: Jadwal)
    fun getAllJadwal(): Flow<List<Jadwal>>
    fun getJadwalById(id: Int): Flow<Jadwal>
}

class LocalRepositoryDokter(private val dokterDao: DokterDao) : RepositoryDokter{
    override suspend fun insertDokter(dokter: Dokter) {
        dokterDao.insertDokter(dokter)
    }

    override fun getAllDokter(): Flow<List<Dokter>> {
        return dokterDao.getAllDokter()
    }
}