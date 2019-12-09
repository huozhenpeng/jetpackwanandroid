package com.example.jetpackwanandroid.repository

import android.app.Application
import androidx.annotation.MainThread
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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

        //初始会加载全部，没找到原因，改用下面的方式
//        val livePagedList= dao.getAll().toLiveData(
//            pageSize=10,
//            boundaryCallback = boundaryCallback
//        )

        val config = PagedList.Config.Builder()
            .setPageSize(15)              // 分页加载的数量
            .setInitialLoadSizeHint(30)   // 初次加载的数量
            .setPrefetchDistance(10)      // 预取数据的距离
            .setEnablePlaceholders(false) // 是否启用占位符
            .build()

        val livePagedList=LivePagedListBuilder(dao.getAll(),config).build()

        return Listing(
            pagedList = livePagedList
        )

    }


    private fun insertResultIntoDb(result:ArticleResult)
    {
        result.let {
            db.runInTransaction{
                dao.insert(result.data.datas)
            }
        }
    }
}