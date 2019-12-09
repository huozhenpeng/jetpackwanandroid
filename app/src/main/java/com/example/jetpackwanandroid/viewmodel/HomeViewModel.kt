package com.example.jetpackwanandroid.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jetpackwanandroid.bean.ArticleItemData
import com.example.jetpackwanandroid.repository.HomeRepository
import com.example.jetpackwanandroid.repository.Listing

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val application: Application
) : ViewModel()
{
    val data=homeRepository.requestData(10)

    public fun click(id:Long)
    {
        Log.e("abc","click")
        homeRepository.updateData(id)
    }
}
