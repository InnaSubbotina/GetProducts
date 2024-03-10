package com.innasubbotina.getproducts.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.innasubbotina.getproducts.databinding.FragmentMainBinding
import com.innasubbotina.getproducts.data.network.ApiFactory
import com.innasubbotina.getproducts.data.network.ProductsApiService
import com.innasubbotina.getproducts.presentation.adapters.ProductAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: ProductAdapter
    private val compositeDisposable = CompositeDisposable()
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
        //binding.rcViewProducts.layoutManager = LinearLayoutManager(activity)
        //adapter = ProductAdapter()
        //binding.rcViewProducts.adapter = adapter

    }

    private fun loadAllProducts(){
        val retrofit = ApiFactory.build()
        val productApi = retrofit.create(ProductsApiService::class.java)
        val disposable = productApi.getAllProducts()
            .subscribeOn(Schedulers.io()) //делаем запрос на второстеп потоке
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val products = it.products
                adapter = ProductAdapter(products)
                binding.rcViewProducts.adapter = adapter
            },{
                //когда нет интернета переходит на экран ошибки
                Toast.makeText(context,"нет ответа от сервера",Toast.LENGTH_SHORT).show()
                /*requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.myHolder, ProductDetailsFragment.newInstance())
                    .addToBackStack(null)
                    .commit()*/
            })
        compositeDisposable.add(disposable)
    }


    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }



}