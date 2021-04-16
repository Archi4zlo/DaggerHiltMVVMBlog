package com.example.daggerhiltmvvmblog.repository

import com.example.daggerhiltmvvmblog.model.Blog
import com.example.daggerhiltmvvmblog.retrofit.BlogRetrofit
import com.example.daggerhiltmvvmblog.retrofit.NetworkMapper
import com.example.daggerhiltmvvmblog.room.BlogDao
import com.example.daggerhiltmvvmblog.room.CacheMapper
import com.example.daggerhiltmvvmblog.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}