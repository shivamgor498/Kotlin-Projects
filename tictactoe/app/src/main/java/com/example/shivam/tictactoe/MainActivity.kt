package com.example.shivam.tictactoe

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }
    var player = 0 // 1 ---> "Yellow" , 0 ---> "Red"
    var gameState = 1
    val array = Array(3, {IntArray(3)})
    fun initialize()
    {
        for(i in 0..2)
            for(j in 0..2)
                    array[i][j] = 2
    }
    fun clickfunction(view : View){
        var iw = view as ImageView
        val tag = view.tag.toString()
        var ix : Int = 0
        var iy : Int = 0
        if(tag == "1")
        {
            ix = 0
            iy = 0
        }
        else if(tag == "2")
        {
            ix = 0
            iy = 1
        }
        else if(tag == "3")
        {
            ix = 0
            iy = 2
        }
        else if(tag == "4")
        {
            ix = 1
            iy = 0
        }
        else if(tag == "5")
        {
            ix = 1
            iy = 1
        }
        else if(tag == "6")
        {
            ix = 1
            iy = 2
        }
        else if(tag == "7")
        {
            ix = 2
            iy = 0
        }
        else if(tag == "8")
        {
            ix = 2
            iy = 1
        }
        else if(tag == "9")
        {
            ix = 2
            iy = 2
        }
        if(array[ix][iy]==2 && gameState==1) {
            iw.translationY = -1000f
            iw.translationX = -1000f
            if (player == 0) {
                iw.setImageResource(R.drawable.index)
                player = 1
                array[ix][iy] = 0
            } else {
                iw.setImageResource(R.drawable.indexy)
                player = 0
                array[ix][iy] = 1
            }
            iw.animate().translationYBy(1000f).translationXBy(1000f).setDuration(1000)
        }
        var cnt = 0
        var winner = 2
        for (i in 0..2)
            for(j in 0..2)
                if(array[i][j]==2)
                    cnt++
        if(array[0][0]==array[0][1] && array[0][1]==array[0][2] && array[0][0]!=2)
            winner = array[0][0]
        else if(array[1][0]==array[1][1] && array[1][1]==array[1][2] && array[1][0]!=2)
            winner = array[1][0]
        else if(array[2][0]==array[2][1] && array[2][1]==array[2][2] && array[2][0]!=2)
            winner = array[2][0]
        else if(array[0][0]==array[1][0] && array[1][0]==array[2][0] && array[0][0]!=2)
            winner = array[0][0]
        else if(array[0][1]==array[1][1] && array[1][1]==array[2][1] && array[0][1]!=2)
            winner = array[0][1]
        else if(array[0][2]==array[1][2] && array[1][2]==array[2][2] && array[0][2]!=2)
            winner = array[0][2]
        else if(array[0][0]==array[1][1] && array[1][1]==array[2][2] && array[0][0]!=2)
            winner = array[0][0]
        else if(array[0][2]==array[1][1] && array[1][1]==array[2][0] && array[0][2]!=2)
            winner = array[0][2]
        var message = "-1"
        if(winner==0)
            message = "!!!Red Wins!!!"
        else if(winner==1)
            message = "!!!Yellow Wins!!!"
        else if(winner==2 && cnt==0)
            message = "It's a Draw"
        if(message!="-1") {
            gameState = 0
            var next = findViewById<LinearLayout>(R.id.playagain)
            var mes = findViewById<TextView>(R.id.winner)
            mes.setText(message)
            next.visibility = View.VISIBLE
        }
    }
    fun resetBoard(view : View)
    {
        var next = findViewById<LinearLayout>(R.id.playagain)
        next.visibility = View.INVISIBLE
        gameState = 1
        player = 0
        for(i in 0..2)
            for(j in 0..2)
                array[i][j] = 2
        findViewById<ImageView>(R.id.image1).setImageResource(0)
        findViewById<ImageView>(R.id.image2).setImageResource(0)
        findViewById<ImageView>(R.id.image3).setImageResource(0)
        findViewById<ImageView>(R.id.image4).setImageResource(0)
        findViewById<ImageView>(R.id.image5).setImageResource(0)
        findViewById<ImageView>(R.id.image6).setImageResource(0)
        findViewById<ImageView>(R.id.image7).setImageResource(0)
        findViewById<ImageView>(R.id.image8).setImageResource(0)
        findViewById<ImageView>(R.id.image9).setImageResource(0)
    }
}
