package com.example.jetpackwanandroid.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_article_cache")
data class ArticleItemData(
    //只保存需要保存的一些数据
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val cache_id : Int=0,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name="chapterName")
    val chapterName: String,

    @ColumnInfo(name = "collect")
    var collect: Boolean,

    @ColumnInfo(name = "article_id")
    val id: Int,

    @ColumnInfo(name = "envelope_pic")
    val envelopePic: String,

    @ColumnInfo(name = "fresh")
    val fresh: Boolean,

    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name="niceDate")
    val niceDate: String,

    @ColumnInfo(name = "super_chapter_name")
    val superChapterName: String,
    //完了看下这种数据类型怎么保存到数据库
    //val tags: List<Any>,
    @ColumnInfo(name="title")
    val title: String,
    @ColumnInfo(name="type")
    val type: Int,
    @ColumnInfo(name="user_id")
    val userId: Int
)