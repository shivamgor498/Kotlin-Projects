package com.example.shivam.audio

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.MediaPlayer
import android.view.View
import android.media.AudioManager
import android.os.Handler
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(){

    var mediaPlayer : MediaPlayer? = null
    var audioManager : AudioManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer.create(this,R.raw.song)
        var maxlen = (mediaPlayer as MediaPlayer).duration
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        var maxVol : Int = (audioManager as AudioManager).getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        var curVol : Int = (audioManager as AudioManager).getStreamVolume(AudioManager.STREAM_MUSIC)
        var volumeControl = findViewById<View>(R.id.Volume) as SeekBar
        var scrubber = findViewById<View>(R.id.scrubber) as SeekBar
        scrubber.max = maxlen
        println(maxVol.toString() + " idefg" + curVol.toString())
        volumeControl.max = maxVol
        volumeControl.progress = curVol
        volumeControl.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.i("start","started")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.i("stop","stoped")
            }
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i("Changed",p1.toString())
                audioManager?.setStreamVolume(AudioManager.STREAM_MUSIC,p1,0)
            }
        })
        scrubber.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.i("start","started")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.i("stop","stoped")
            }
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i("Changed",p1.toString())
                (mediaPlayer as MediaPlayer).seekTo(p1)
            }
        })
        var handle : Handler? = null
        runOnUiThread(object : Runnable {
            override fun run() {
                if(mediaPlayer!=null)
                    scrubber.progress = (mediaPlayer as MediaPlayer).currentPosition
                handle?.postDelayed(this,1000)
            }
        })
    }
    fun playFunction(view : View){
        mediaPlayer!!.start()
    }
    fun pauseFunction(view: View) {
        mediaPlayer!!.pause()
    }
}
