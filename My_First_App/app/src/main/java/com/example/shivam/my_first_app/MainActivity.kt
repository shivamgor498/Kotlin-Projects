package com.example.shivam.my_first_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.EditText

const val EXTRA_MESSAGE = "com.example.My_First_App.MESSAGE"
const val tag = "MainActivity"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(tag,"On Create")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.v(tag,"On Post Create")
    }
    override fun onRestart() {
        super.onRestart()
        Log.v(tag,"On Restart")
    }
    override fun onStart() {
        super.onStart()
        Log.v(tag,"On Start")
    }

    override fun onResume() {
        super.onResume()
        Log.v(tag,"On Resume")
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.v(tag,"On Post Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.v(tag,"On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.v(tag,"On Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag,"On Destroy")
    }
    fun sendMessage(view : View)
    {
        val  message = findViewById<EditText>(R.id.editText).text.toString()
        val intent = Intent(this,DisplayMessageactivity::class.java).apply {
            putExtra(EXTRA_MESSAGE,message)
        }
        startActivity(intent)
    }
}