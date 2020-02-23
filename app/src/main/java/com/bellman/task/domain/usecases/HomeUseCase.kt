package com.bellman.task.domain.usecases

import com.bellman.task.domain.core.mapNetworkErrors
import com.bellman.task.domain.repositories.HomeRepository
import com.bellman.task.domain.repositories.homeRepositoryImp
import com.bellman.task.entity.Data
import com.bellman.task.entity.Entity
import io.reactivex.Observable

val homeUseCaseImp by lazy {
    HomeUseCase()
}


class HomeUseCase(private val homeRepository: HomeRepository = homeRepositoryImp) {

    fun getHomeData(): Observable<Data> {
        return homeRepository.getHomeData().map {
            it.data
        }
            .mapNetworkErrors()
    }


}