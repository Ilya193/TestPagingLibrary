package com.xlwe.testpaginglibrary

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xlwe.testpaginglibrary.network.ApiRequests
import com.xlwe.testpaginglibrary.network.model.Result
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class TestPagingSource : PagingSource<Int, Result>() {
    private val url = "https://rickandmortyapi.com/api/"
    private val apiRequests = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiRequests::class.java)

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {
            val currentPage = params.key ?: 1
            val response = apiRequests.getCharacters(currentPage)

            val responseData = mutableListOf<Result>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            return LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}