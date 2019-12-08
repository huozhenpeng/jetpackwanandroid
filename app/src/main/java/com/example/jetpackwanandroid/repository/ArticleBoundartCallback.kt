package com.example.jetpackwanandroid.repository

import androidx.paging.PagedList
import androidx.paging.PagingRequestHelper
import com.example.jetpackwanandroid.bean.ArticleItemData
import com.example.jetpackwanandroid.bean.ArticleResult
import com.example.jetpackwanandroid.http.HttpManager
import io.reactivex.functions.Consumer
import java.util.concurrent.Executors

class ArticleBoundartCallback(
    private val handleResponse: (ArticleResult) -> Unit
) : PagedList.BoundaryCallback<ArticleItemData>() {

    private val helper = PagingRequestHelper(Executors.newSingleThreadExecutor())
    override fun onItemAtEndLoaded(itemAtEnd: ArticleItemData) {

        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER)
        {
            HttpManager.getInstance().wanApi.getArticles(
                pageNo = 1
            ).subscribe(
                Consumer {
                    insertItemsIntoDb(it)
            }, Consumer {

            })
        }
    }

    private fun insertItemsIntoDb(article:ArticleResult) {
        Executors.newSingleThreadExecutor().execute {
            handleResponse(article)
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: ArticleItemData) {
        super.onItemAtFrontLoaded(itemAtFront)
    }

    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL)
        {
            HttpManager.getInstance().wanApi.getArticles(
                pageNo = 1
            ).subscribe(
                Consumer {
                    insertItemsIntoDb(it)
                }, Consumer {

                })
        }
    }
}