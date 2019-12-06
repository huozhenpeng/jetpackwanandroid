package com.example.jetpackwanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpackwanandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var mBind : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //数据绑定，两步
        mBind=DataBindingUtil.setContentView(this,R.layout.activity_main)
        mBind.lifecycleOwner=this


    }
}
