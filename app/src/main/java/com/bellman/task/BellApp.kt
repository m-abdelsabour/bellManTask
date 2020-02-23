package com.bellman.task

import android.app.Application
import com.bellman.task.domain.core.Domain

class BellApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Domain.integrateWith(this)
    }

}