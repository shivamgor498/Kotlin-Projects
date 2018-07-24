package com.example.shivam.eggtimer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var seekBar : View? = null
    private lateinit var timer: CountDownTimer
    var flag = 0
    private lateinit var mediaPlayer : MediaPlayer
    fun clickFunction(view: View){
        if(flag==0)
        {
            seekBar!!.isEnabled = false
            timerButton.text = "Stop"
            starttimer()
            flag = 1
        }
        else {
            flag = 0
            seekBar!!.isEnabled = true
            timerButton.text = "Go"
            timerText.text = "00:30"
            timer.cancel()
            (seekBar as SeekBar)?.progress = 30
        }
    }
    fun starttimer()
    {
        timer = object : CountDownTimer((seekBar as SeekBar)?.progress.toLong()*1000+100,1000){
            override fun onFinish() {
                timerText.text = "00:00"
                flag = 0
                mediaPlayer = MediaPlayer.create(applicationContext,R.raw.piratesringtone)
                mediaPlayer.start()
                seekBar!!.isEnabled = true
                timerButton.text = "Go"
            }
            override fun onTick(p0: Long) {
                timerText((p0/1000).toInt())
            }
        }.start()
    }
    fun timerText(p1 : Int)
    {
        var minutes : Int = p1/60
        var seconds : Int = p1- minutes*60
        var ssecond : String = seconds.toString()
        var sminute : String = minutes.toString()
        if(minutes<10)
            sminute = "0" + sminute
        if(seconds<10)
            ssecond = "0" + ssecond
        timerText.text = sminute + ":" + ssecond
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar = findViewById<View>(R.id.seekBar) as SeekBar
        (seekBar as SeekBar)?.max = 600
        (seekBar as SeekBar)?.progress = 30
        timerText.text = "00:30"
        (seekBar as SeekBar)?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                timerText(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }
        })
    }
}
