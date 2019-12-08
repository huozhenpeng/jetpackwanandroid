package com.example.jetpackwanandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackwanandroid.R
import com.example.jetpackwanandroid.bean.ArticleItemData
import com.example.jetpackwanandroid.databinding.ItemCommonBinding

class HomeAdapter: PagedListAdapter<ArticleItemData, HomeAdapter.HomeViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_common,parent,false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.binding.detail=getItem(position)

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

                return oldItem.id==newItem.id
            }

            override fun areItemsTheSame(
                oldItem: ArticleItemData,
                newItem: ArticleItemData
            ): Boolean {
                return oldItem==newItem
            }

        }
    }

}
