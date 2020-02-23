package com.bellman.task.domain.repositories

import com.bellman.task.entity.Entity
import io.reactivex.Observable

val homeRepositoryImp by lazy {
    HomeRepositoryImp()
}


interface HomeRepository {
    fun getHomeData(): Observable<Entity>
}