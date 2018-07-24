package com.example.shivam.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    var flag = 0
    fun clickfunction(view : View)
    {
        if(flag==0) {
            val iw = findViewById<ImageView>(R.id.imageView)
            iw.setImageResource(R.drawable.temp)
            flag = 1
        }
        else
        {
            val iw = findViewById<ImageView>(R.id.imageView)
            iw.setImageResource(R.drawable.debo)
            flag = 0
        }
    }
}
