package com.example.jetpackwanandroid.repository

import android.util.Log
import androidx.paging.PagedList
import androidx.paging.PagingRequestHelper
import com.example.jetpackwanandroid.bean.ArticleItemData
import com.example.jetpackwanandroid.bean.ArticleResult
import com.example.jetpackwanandroid.http.HttpManager
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class ArticleBoundartCallback(
    private val handleResponse: (ArticleResult) -> Unit
) : PagedList.BoundaryCallback<ArticleItemData>() {

    private val helper = PagingRequestHelper(Executors.newSingleThreadExecutor())
    override fun onItemAtEndLoaded(itemAtEnd: ArticleItemData) {
        Log.e("abc","onItemAtEndLoaded:")
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER)
        {
            Log.e("abc","onItemAtEndLoaded:"+Thread.currentThread())
            HttpManager.getInstance().wanApi.getArticles(
                pageNo = 1
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                Consumer {
                    insertItemsIntoDb(it)
            }, Consumer {

            })
        }
//        HttpManager.getInstance().wanApi.getArticles(
//            pageNo = 1
//        ).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread()).subscribe(
//                Consumer {
//                    insertItemsIntoDb(it)
//                }, Consumer {
//
//                })
    }

    private fun insertItemsIntoDb(article:ArticleResult) {
        Executors.newSingleThreadExecutor().execute {
            handleResponse(article)
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: ArticleItemData) {
        Log.e("abc","onItemAtFrontLoaded:"+Thread.currentThread())
        super.onItemAtFrontLoaded(itemAtFront)
    }

    override fun onZeroItemsLoaded() {
        Log.e("abc","onZeroItemsLoaded:")
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL)
        {
            Log.e("abc","onZeroItemsLoaded:"+Thread.currentThread())
            HttpManager.getInstance().wanApi.getArticles(
                pageNo = 1
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                Consumer {
                    insertItemsIntoDb(it)
                }, Consumer {
                        Log.e("abc","失败:"+it.toString())
                })
        }
    }
}