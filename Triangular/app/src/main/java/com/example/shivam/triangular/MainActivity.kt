package com.example.shivam.triangular

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    class answ(val number:Int) {
        fun cal(): String {
            val squa = sqrt(number.toDouble()).toInt()
            val temp = 8 * number + 1
            val tria = sqrt(temp.toDouble()).toInt()
            var an: Boolean = false
            var an1: Boolean = false
            if ((squa * squa).toInt() == number)
                an = true
            if ((tria * tria).toInt() == temp)
                an1 = true
            if (an && an1)
                return "Both!"
            else if (an)
                return "Square Number"
            else if (an1)
                return "Triangle Number"
            return "None"
        }
    }
    fun clickfunction(view : View)
    {
        if(findViewById<EditText>(R.id.input).text.toString().isEmpty())
        {
            Toast.makeText(this,"Please Enter a message!",Toast.LENGTH_LONG).show()
        }
        else
        {
            var number = findViewById<EditText>(R.id.input).text.toString().toInt()
            var a = answ(number)
            Toast.makeText(this,a.cal(),Toast.LENGTH_LONG).show()
        }
    }

}
