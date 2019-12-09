package com.example.jetpackwanandroid.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackwanandroid.R
import com.example.jetpackwanandroid.bean.ArticleItemData
import com.example.jetpackwanandroid.databinding.ItemCommonBinding
import com.example.jetpackwanandroid.viewmodel.HomeViewModel

class HomeAdapter(
    private val viewModel: HomeViewModel
): PagedListAdapter<ArticleItemData, HomeAdapter.HomeViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_common,parent,false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.binding.detail=getItem(position)
        holder.binding.viewmodel=viewModel
        holder.binding.executePendingBindings()

    }


    class HomeViewHolder (val binding:ItemCommonBinding) :RecyclerView.ViewHolder(binding.root)
    {

    }

    companion object{
        private val DIFF_CALLBACK=object : DiffUtil.ItemCallback<ArticleItemData>()
        {
            override fun areContentsTheSame(
                oldItem: ArticleItemData,
                newItem: ArticleItemData
            ): Boolean {

                //Log.e("abc","oldItem.id==newItem.id------>"+(oldItem.id==newItem.id))
                return oldItem.cache_id==newItem.cache_id
            }

            override fun areItemsTheSame(
                oldItem: ArticleItemData,
                newItem: ArticleItemData
            ): Boolean {
                //Log.e("abc","oldItem==newItem------>"+(oldItem==newItem))
                return oldItem==newItem
            }

        }
    }

}

