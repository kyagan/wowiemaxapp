package com.kaanyagan.wowiemax.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kaanyagan.wowiemax.data.entity.model.Category
import com.kaanyagan.wowiemax.databinding.CategoryListItemBinding

class CategoryAdapter(val context: Context, val categories:Array<Category>, val onClick:(category:Category)-> Unit):RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    class MyViewHolder(view:CategoryListItemBinding):ViewHolder(view.root){
        var tvCategoryName = view.tvCategoryName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CategoryListItemBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = categories[position]
        holder.tvCategoryName.text = category.name
        holder.itemView.setOnClickListener {
            onClick(category)
        }
    }
}