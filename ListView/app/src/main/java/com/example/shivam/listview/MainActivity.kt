package com.example.shivam.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myList : ListView = findViewById<View>(R.id.listView) as ListView
        var arrayList : ArrayList<String> = arrayListOf("Shivam","Kushal","Purvi","Chetan")
        var arrayAdapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList)
        myList.adapter = arrayAdapter
        myList.setOnItemClickListener { _ : AdapterView<*>, _ : View, position: Int , _: Long ->
            Toast.makeText(this,arrayList.get(position),Toast.LENGTH_SHORT).show()
        }
    }
}
