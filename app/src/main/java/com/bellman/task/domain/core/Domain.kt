package com.bellman.task.domain.core

import android.app.Application
import java.lang.ref.WeakReference

object Domain {
    private lateinit var applicationReference : WeakReference<Application>

    fun integrateWith(application: Application){
        applicationReference = WeakReference(application)
    }

    fun getApplication() = applicationReference.get()!!
}
