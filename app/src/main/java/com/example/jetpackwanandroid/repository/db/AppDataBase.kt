package com.example.jetpackwanandroid.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.jetpackwanandroid.Constants
import com.example.jetpackwanandroid.bean.ArticleItemData

//参考sunflower官方项目
@Database(entities = [ArticleItemData::class],version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getArticleItemDao():ArticleDao

    companion object{
        @Volatile
        private  var instance : AppDataBase?=null

        fun  getInstance(context: Context) : AppDataBase
        {
            return instance?: synchronized(this){
                instance?:buildDatabase(context).also{ instance=it}
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {

            return Room.databaseBuilder(context,AppDataBase::class.java, Constants.DATABASE_NAME)
                .addCallback(object :RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //下面两句暂时不知道什么作用,貌似是初始化数据库用的
                        //val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        //WorkManager.getInstance(context).enqueue(request)
                    }
                }).build()
        }
    }
}