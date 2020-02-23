package com.bellman.task.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bellman.task.domain.core.addTo
import com.bellman.task.domain.usecases.HomeUseCase
import com.bellman.task.domain.usecases.homeUseCaseImp
import com.bellman.task.entity.Data
import com.bellman.task.entity.Entity
import com.bellman.task.presentation.core.androidMainThreadScheduler
import com.bellman.task.presentation.core.schedulerIo
import com.kotlin.firstkotlin.viewmodel.BaseViewModel


class HomeViewModel(private val homeUseCase: HomeUseCase = homeUseCaseImp) : BaseViewModel() {
    private val homeLiveData = MutableLiveData<Data>()

    fun getHomeData() {
        homeUseCaseImp.getHomeData()
            .subscribeOn(schedulerIo)
            .observeOn(androidMainThreadScheduler)
            .doOnSubscribe {
                setLoading(true)
            }
            .subscribe({
                setLoading(false)
                homeLiveData.value = it
            }, {
                setLoading(false)
                setError(it)
            }, {}).addTo(disposable)
    }

    fun getLiveData(): LiveData<Data> = homeLiveData

}