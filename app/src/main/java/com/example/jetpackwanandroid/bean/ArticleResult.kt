package com.example.jetpackwanandroid.bean

data class ArticleResult(
    val `data`: Data,
    val errorCode: Int,
    val errorMsg: String
)

data class Data(
    val curPage: Int,
    val articleItemData: List<ArticleItemData>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

