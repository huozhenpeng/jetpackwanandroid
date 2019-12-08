package com.example.jetpackwanandroid.repository

import android.app.Application
import androidx.annotation.MainThread
import androidx.paging.DataSource
import androidx.paging.toLiveData
import com.example.jetpackwanandroid.bean.ArticleItemData
import com.example.jetpackwanandroid.bean.ArticleResult
import com.example.jetpackwanandroid.repository.db.AppDataBase

class HomeRepository(
    private val application: Application
) {
    private val db=AppDataBase.getInstance(application)
    private val dao= db.getArticleItemDao()

    /**
     * 初始请求数据
     */
    @MainThread
    fun requestData(pageSize:Int) : Listing<ArticleItemData>
    {
        val boundaryCallback = ArticleBoundartCallback(
            handleResponse = this::insertResultIntoDb
        )

        val livePagedList= dao.getAll().toLiveData(
            pageSize=pageSize,
            boundaryCallback = boundaryCallback
        )

        return Listing(
            pagedList = livePagedList
        )

    }


    private fun insertResultIntoDb(result:ArticleResult)
    {
        result.let {
            db.runInTransaction{
                dao.insert(result.data.articleItemData)
            }
        }
    }
}