package com.innasubbotina.getproducts.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.innasubbotina.getproducts.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* supportFragmentManager
            .beginTransaction()
            .add(R.id.myHolder, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()*/

    }
}