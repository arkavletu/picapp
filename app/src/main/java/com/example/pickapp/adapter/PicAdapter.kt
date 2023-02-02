package com.example.pickapp.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pickapp.Category
import com.example.pickapp.dao.CategoriesDao
import com.example.pickapp.databinding.CategoryBinding
import com.example.pickapp.databinding.HitsBinding
import com.example.pickapp.dto.Hits
import com.example.pickapp.dto.Reply

class PicAdapter (
    private val dao: CategoriesDao

    ): ListAdapter<Hits, PicAdapter.ViewHolder>(DiffSearcher) {
    var data:List<Hits> = emptyList()
    val urls: List<String> = data.map { it.previewURL!! }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HitsBinding.inflate(inflater, parent, false)

        return ViewHolder(binding, dao)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
        data +(getItem(position))
        Glide.with(holder.itemView).load(urls.get(position)).into(holder.view)
        holder.itemView.setOnClickListener {
            //dao.clicked(getItem(position).value)
        }
    }


    inner class ViewHolder(
        private val binding: HitsBinding,
        listener: CategoriesDao
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var hits: Hits
        val view = binding.smallPic

        fun bind(hits:Hits) {
            this.hits = hits
            Log.d(TAG, "bind: ${hits.id}")
            with(binding){

            }




        }
    }

    private object DiffSearcher : DiffUtil.ItemCallback<Hits>() {
        override fun areItemsTheSame(oldItem: Hits, newItem: Hits) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Hits, newItem: Hits) = oldItem == newItem

    }


}