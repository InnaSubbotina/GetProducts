package com.innasubbotina.getproducts.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.innasubbotina.getproducts.databinding.ProductsListItemBinding
import com.innasubbotina.getproducts.data.models.Product
import com.squareup.picasso.Picasso

class Holder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ProductsListItemBinding.bind(view)
    fun bindData(product: Product) = with(binding) {
        //imViewUser.setImageResource(R.drawable.gus)
        tvName.text = product.title
        tvDescription.text = product.description
        Picasso.get().load(product.thumbnail).into(imViewProduct)
    }
}