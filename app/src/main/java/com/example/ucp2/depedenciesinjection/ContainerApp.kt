package com.example.ucp2.depedenciesinjection

import android.content.Context
import com.example.ucp2.data.database.HealtDatabase
import com.example.ucp2.repository.LocalRepositoryDokter
import com.example.ucp2.repository.LocalRepositoryJadwal
import com.example.ucp2.repository.RepositoryDokter
import com.example.ucp2.repository.RepositoryJadwal

interface InterfaceContainerApp {
    val repositoryDokter: RepositoryDokter
    val repositoryJadwal: RepositoryJadwal
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDokter: RepositoryDokter by lazy{
        LocalRepositoryDokter(HealtDatabase.getDatabase(context).dokterDao())
    }

    override val repositoryJadwal: RepositoryJadwal by lazy{
        LocalRepositoryJadwal(HealtDatabase.getDatabase(context).jadwalDao())
    }
}