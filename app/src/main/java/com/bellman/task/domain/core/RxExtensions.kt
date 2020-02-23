package com.bellman.task.domain.core

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun <T> Observable<T>.mapNetworkErrors(): Observable<T> {
    return onErrorResumeNext { error: Throwable ->
        when (error) {
            is SocketTimeoutException -> Observable.error(TimeoutException())
            is UnknownHostException, is ConnectException -> Observable.error(
                ServerUnreachableException()
            )
            is HttpException -> {
                when {
                    error.code() == 401 -> Observable.error(UnAuthorizedException())
                    error.code() == 400 -> Observable.error(BadRequest())
                    error.code() == 404 -> Observable.error(NotFound())
                    error.code() == 429 -> Observable.error(ManyRequest())
                    else -> Observable.error(ServerErrorException())
                }
            }
            else -> Observable.error(error)
        }
    }
}


fun <T> Single<T>.mapNetworkErrors(): Single<T> {
    return onErrorResumeNext { error ->
        when (error) {
            is SocketTimeoutException -> Single.error(TimeoutException())
            is UnknownHostException, is ConnectException -> Single.error(ServerUnreachableException())
            is HttpException -> {
                when {
                    error.code() == 401 -> Single.error(UnAuthorizedException())
                    else -> Single.error(ServerErrorException())
                }
            }
            else -> Single.error(error)
        }
    }
}

fun Completable.mapNetworkErrors(): Completable {
    return onErrorResumeNext { error ->
        when (error) {
            is SocketTimeoutException -> Completable.error(TimeoutException())
            is UnknownHostException, is ConnectException -> Completable.error(
                ServerUnreachableException()
            )
            is HttpException -> {
                when {
                    error.code() == 401 -> Completable.error(UnAuthorizedException())
                    else -> Completable.error(ServerErrorException())
                }
            }
            else -> Completable.error(error)
        }
    }
}