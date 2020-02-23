package com.bellman.task.domain.repositories

import com.bellman.task.domain.gateways.RetroService
import com.bellman.task.domain.gateways.retroServiceApi
import com.bellman.task.entity.Entity
import io.reactivex.Observable

class HomeRepositoryImp(private val retroService: RetroService = retroServiceApi) : HomeRepository {
    override fun getHomeData(): Observable<Entity> = retroServiceApi.getHomeData()
}