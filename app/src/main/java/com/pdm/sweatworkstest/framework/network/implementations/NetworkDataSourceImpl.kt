package com.pdm.sweatworkstest.framework.network.implementations

import com.pdm.sweatworkstest.core.data.abstraction.NetworkDataSource
import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.core.domain.util.DataState
import com.pdm.sweatworkstest.framework.network.ApiService
import com.pdm.sweatworkstest.framework.network.base.BaseNetworkDataSource
import com.pdm.sweatworkstest.framework.network.base.Result
import com.pdm.sweatworkstest.framework.network.mappers.UserMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkDataSourceImpl(
    private val apiService: ApiService,
    private val userMapper: UserMapper
) : NetworkDataSource, BaseNetworkDataSource() {
    override suspend fun getUsers(page :Int): DataState<List<User>> {
        return try {
            withContext(Dispatchers.IO) {
                val result = getResult { apiService.getNews(page) }
                when (result.status) {
                    Result.Status.SUCCESS -> {
                        val entities = result.data!!.results
                        val users = userMapper.mapFromEntityList(entities)
                        DataState.Success(users)
                    }
                    Result.Status.ERROR -> {
                        DataState.Error(Exception(result.message!!))
                    }
                }
            }
        } catch (e: java.lang.Exception) {
            DataState.Error(e)
        }
    }
}