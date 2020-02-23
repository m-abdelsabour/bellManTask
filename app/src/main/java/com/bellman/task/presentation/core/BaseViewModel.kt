package com.kotlin.firstkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Throwable>()
    val disposable = CompositeDisposable()

    fun setLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }

    fun setError(error: Throwable) {
        this.error.value = error
    }


    fun getLoading(): LiveData<Boolean> = isLoading

    fun getError(): LiveData<Throwable> = error

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}