package com.innasubbotina.productapp.data.network

import com.innasubbotina.productapp.data.models.AllProductsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApiService {
    @GET("products")
    fun getAllProducts(
        @Query(QUERY_PARAM_LIMIT) limit: Int = 20
    ) : Single<AllProductsResponse>


    /*
    @GET("products")
    fun getAllProducts(
        @Query(QUERY_PARAM_LIMIT) limit: Int = 20
    ) : Observable<AllProductsResponse>
     */

    companion object{
        private const val QUERY_PARAM_LIMIT = "limit"
    }
}