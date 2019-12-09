package com.example.jetpackwanandroid.repository.db

import androidx.paging.DataSource
import androidx.room.*
import com.example.jetpackwanandroid.bean.ArticleItemData

@Dao
interface ArticleDao {

    @Query("SELECT * FROM home_article_cache")
    fun getAll(): DataSource.Factory<Int,ArticleItemData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data:List<ArticleItemData>)


    @Update
    fun update(articleItemData: ArticleItemData)

    @Query("SELECT * FROM HOME_ARTICLE_CACHE WHERE article_id=:id")
    fun getSingle(id:Int):ArticleItemData


}