package com.innasubbotina.getproducts.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.innasubbotina.getproducts.R
import com.innasubbotina.getproducts.data.models.Product

class ProductAdapter(
    private var productList: List<Product>,
    private val listener: OnClickProductDetails
) : ListAdapter<Product, Holder>(Comparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_list_item,parent,false)
        return Holder(view)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(productList[position],listener)
    }
    override fun getItemCount(): Int {
        return productList.size
    }
}