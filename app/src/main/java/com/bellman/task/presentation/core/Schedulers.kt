package com.bellman.task.presentation.core

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

val androidMainThreadScheduler: Scheduler = AndroidSchedulers.mainThread()

val schedulerIo: Scheduler = Schedulers.io()