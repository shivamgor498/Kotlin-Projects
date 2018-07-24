package com.example.shivam.phrases

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.view.menu.MenuWrapperFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun clickFunction(view: View)
    {
        var ourId : String = view.resources.getResourceEntryName(view.id)
        var resourceId : Int = resources.getIdentifier(ourId,"raw" , "com.example.shivam.phrases")
        var mediaPlayer : MediaPlayer = MediaPlayer.create(this,resourceId)
        mediaPlayer.start()
    }
}
