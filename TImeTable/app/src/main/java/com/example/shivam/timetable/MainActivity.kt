package com.example.shivam.timetable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    fun generate(value : Int)
    {
        var listView = findViewById<View>(R.id.listView) as ListView
        var arrayList = arrayListOf<String>()
        for( i in 1..10)
            arrayList.add((i*value).toString())
        var arrayAdapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList)
        listView.adapter = arrayAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var seekBar = findViewById<View>(R.id.seekBar) as SeekBar
        seekBar.max = 20
        seekBar.progress = 10
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var times : Int
                if(p1<1)
                {
                    seekBar.progress = 1
                    times = 1
                }
                else
                    times = p1
                Log.i("data",p1.toString())
                generate(times)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        generate(10)

    }
}
