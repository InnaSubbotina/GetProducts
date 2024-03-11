package com.innasubbotina.getproducts.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.search.SearchView
import com.innasubbotina.getproducts.R
import com.innasubbotina.getproducts.data.models.Product
import com.innasubbotina.getproducts.data.network.ApiFactory
import com.innasubbotina.getproducts.data.network.ProductsApiService
import com.innasubbotina.getproducts.databinding.FragmentMainBinding
import com.innasubbotina.getproducts.presentation.adapters.OnClickProductDetails
import com.innasubbotina.getproducts.presentation.adapters.ProductAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainFragment : Fragment(), OnClickProductDetails {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: ProductAdapter
    private val compositeDisposable = CompositeDisposable()
    private val retrofit = ApiFactory.build()
    private val navController by lazy {
        findNavController()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcViewProducts.layoutManager = LinearLayoutManager(activity)
        loadAllProducts()
        setSearchListener()
    }
   private fun loadAllProducts(){
        val productApi = retrofit.create(ProductsApiService::class.java)
        val disposable = productApi.getAllProducts()
            .subscribeOn(Schedulers.io()) //делаем запрос на второстеп потоке
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val products = it.products
                setListToAdapter(products)
            },{
                //когда нет интернета переходит на экран ошибки
                Toast.makeText(context,"нет ответа от сервера",Toast.LENGTH_SHORT).show()
            })
        compositeDisposable.add(disposable)
    }
    private fun setListToAdapter(list: List<Product>){
        adapter = ProductAdapter(list,this)
        binding.rcViewProducts.adapter = adapter
    }
    fun searchProduct(text: String) {
        val productApi = retrofit.create(ProductsApiService::class.java)
        val disposable01 = productApi.searchProducts(text)
            .subscribeOn(Schedulers.io()) //делаем запрос на второстеп потоке
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val productsSearch = it.products
                setListToAdapter(productsSearch)
            },{
                //когда нет интернета переходит на экран ошибки
                Toast.makeText(context,"нет ответа от сервера111",Toast.LENGTH_SHORT).show()
            })
        compositeDisposable.add(disposable01)
    }
    fun setSearchListener() {
        binding.searchv.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                searchProduct(newText)
                return true
            }
        })
    }

    /*companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
*/
    override fun onClickProductDetails(bungleProduct: Bundle) {
        navController.navigate(R.id.productDetailsFragment,bungleProduct)
    }

}