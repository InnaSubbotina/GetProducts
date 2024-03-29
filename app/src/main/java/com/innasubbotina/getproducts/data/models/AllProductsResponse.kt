package com.innasubbotina.getproducts.data.models

import com.google.gson.annotations.SerializedName

data class AllProductsResponse(
    @SerializedName("products")
    val products: List<Product>
)