package com.example.jetpackwanandroid.repository.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackwanandroid.bean.ArticleItemData

@Dao
interface ArticleDao {

    @Query("SELECT * FROM home_article_cache")
    fun getAll(): DataSource.Factory<Int,ArticleItemData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data:List<ArticleItemData>)


}