package com.innasubbotina.getproducts.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.innasubbotina.getproducts.R
import com.innasubbotina.getproducts.data.models.Product
import com.innasubbotina.getproducts.databinding.FragmentProductDetailsBinding
import com.squareup.picasso.Picasso


class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private var product: Product? = null
    private val navController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getSerializable("prod") as Product
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProductDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProductData()
        binding.backButton.setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
    }

    private fun setProductData() = with(binding) {
        Picasso.get().load(product?.thumbnail).into(imProduct)
        tvTitle.text = product?.title
        tvDescription.text = product?.description
        tvPrice.text = product?.price.toString()

    }


   /* companion object {
        fun newInstance(): ProductDetailsFragment {
            return ProductDetailsFragment()
        }
    }*/

}