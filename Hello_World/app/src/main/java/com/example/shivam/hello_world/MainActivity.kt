package com.example.shivam.hello_world

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.jetbrains.anko.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn = findViewById(R.id.button) as Button
        btn.setOnClickListener{ toast( "Hello_World!")}
        textView.setText("Hi There!")
    }
}
