package com.example.ucp2

import android.app.Application
import com.example.ucp2.depedenciesinjection.ContainerApp

class HealthApp :Application() {
    lateinit var containerApp: ContainerApp //Fungsinya untuk menyimoan instance

    override fun onCreate(){
        super.onCreate()
        containerApp = ContainerApp(this) //Membuat instance ContainerAPp
        //instance adalah object yang dibuat dari class
    }
}