package com.example.jetpackwanandroid.http

import com.example.jetpackwanandroid.bean.ArticleResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author: Sbingo666
 * Date:   2019/4/3
 */
interface WanApi {

    /**
     * 首页文章列表
     */
    @GET("article/list/{pageNo}/json")
    fun getArticles(@Path("pageNo") pageNo: Int): Observable<ArticleResult>


}
