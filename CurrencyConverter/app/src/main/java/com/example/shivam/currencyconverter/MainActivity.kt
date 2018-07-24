package com.example.shivam.currencyconverter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun clickfunction(view : View)
    {
        var value = findViewById<EditText>(R.id.CurrencyInput).text.toString().toDouble()
        value = value * (0.015)
        val fvalue = value.toString()
        Toast.makeText(this,fvalue,Toast.LENGTH_LONG).show()
    }
}
