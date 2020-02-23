package com.bellman.task.domain.gateways

import com.bellman.task.entity.Entity
import io.reactivex.Observable
import retrofit2.http.GET


val retroServiceApi: RetroService by lazy {

    retrofit.create(RetroService::class.java)
}

interface RetroService {
    @GET("home")
    fun getHomeData(): Observable<Entity>

}