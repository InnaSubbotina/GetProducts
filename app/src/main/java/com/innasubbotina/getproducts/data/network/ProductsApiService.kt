package com.innasubbotina.getproducts.data.network

import com.innasubbotina.getproducts.data.models.AllProductsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApiService {
    @GET("products")
    fun getAllProducts(
        @Query(QUERY_PARAM_LIMIT) limit: Int = 20
    ) : Single<AllProductsResponse>

    @GET("products/search")
    fun searchProducts(
        @Query("q") name: String
    ) : Single<AllProductsResponse>


    companion object{
        private const val QUERY_PARAM_LIMIT = "limit"
    }
}