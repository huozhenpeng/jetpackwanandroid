package com.example.jetpackwanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackwanandroid.adapter.HomeAdapter
import com.example.jetpackwanandroid.databinding.ActivityMainBinding
import com.example.jetpackwanandroid.repository.HomeRepository
import com.example.jetpackwanandroid.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private  lateinit var mBind : ActivityMainBinding

    private val mViewModel : HomeViewModel by  viewModels{

        object : ViewModelProvider.Factory
        {
            private val homeViewModel= HomeRepository(application)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                return HomeViewModel(homeViewModel,application) as T
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //数据绑定，两步
        mBind=DataBindingUtil.setContentView(this,R.layout.activity_main)
        mBind.lifecycleOwner=this

        val adapter=HomeAdapter()
        mBind.recyclerView.adapter=adapter

        mViewModel.data.pagedList.observe(this, Observer {

            adapter.submitList(it){
//                val layoutManager = (mBind.recyclerView.layoutManager as LinearLayoutManager)
//                val position = layoutManager.findFirstCompletelyVisibleItemPosition()
//                if (position != RecyclerView.NO_POSITION) {
//                    mBind.recyclerView.scrollToPosition(position)
//                }
            }


        })




    }
}
