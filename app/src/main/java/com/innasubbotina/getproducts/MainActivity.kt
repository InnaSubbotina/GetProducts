package com.innasubbotina.getproducts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.innasubbotina.getproducts.presentation.ui.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

/*supportFragmentManager
           .beginTransaction()
           .add(R.id.myHolder, MainFragment.newInstance())
           .addToBackStack(null)
           .commit()*/
