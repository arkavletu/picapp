package com.example.pickapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pickapp.Category
import com.example.pickapp.dao.CategoriesDao
import com.example.pickapp.databinding.CategoryBinding

class CategoriesAdapter(
    private val dao: CategoriesDao
): ListAdapter<Category, CategoriesAdapter.ViewHolder>(DiffSearcher) {
//    var data = Categories.array

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryBinding.inflate(inflater, parent, false)

        return ViewHolder(binding, dao)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
        //data.add(getItem(position))
        holder.itemView.setOnClickListener {

            dao.clicked(getItem(position).value)
        }
    }


    inner class ViewHolder(
        private val binding: CategoryBinding,
        listener: CategoriesDao
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var category: Category


        fun bind(category: Category) {
            this.category = category
            with(binding)
            {

             value.text = category.value

            }
        }
    }

    private object DiffSearcher : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem.value == newItem.value

        override fun areContentsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem

    }

}