package com.example.shivam.brain_trainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import org.w3c.dom.Text
import java.lang.Math.random
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.nextUp

class MainActivity : AppCompatActivity() {
    private lateinit var start : View
    private lateinit var timerText : View
    private lateinit var resultText : View
    private lateinit var questionText : View
    private lateinit var button1 : View
    private lateinit var button2 : View
    private lateinit var button3 : View
    private lateinit var button4 : View
    private lateinit var relativeLayout: RelativeLayout
    private lateinit var answerText : View
    private lateinit var timer : CountDownTimer
    private lateinit var reset : Button
    var correctAnswerIndex : Int = -1
    var correctAnswer : Int = -1
    var rightAnswer: Int = 0
    var totalquestion: Int = 1
    var count : Int = 30
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start = findViewById<TextView>(R.id.start)
        timerText = findViewById<TextView>(R.id.timerText)
        resultText = findViewById<TextView>(R.id.resultText)
        questionText = findViewById<TextView>(R.id.questionText)
        button1 = findViewById<Button>(R.id.button1)
        button2 = findViewById<Button>(R.id.button2)
        button3 = findViewById<Button>(R.id.button3)
        button4 = findViewById<Button>(R.id.button4)
        relativeLayout = findViewById(R.id.relativeLayout)
        answerText = findViewById<TextView>(R.id.answerText)
        reset = findViewById(R.id.Reset)
    }

    fun start(view : View)
    {
        start.visibility = View.INVISIBLE
        relativeLayout.visibility = View.VISIBLE
        relativeLayout.bringToFront()
        generate()
        timer = object : CountDownTimer(30100,1000){
            override fun onFinish() {
                (timerText as TextView).text = "0s"
                finished()
            }

            override fun onTick(p0: Long) {
                count = count - 1
                (timerText as TextView).text = count.toString() + "s"
            }
        }.start()
    }
    fun generate()
    {
        var number1 = Random().nextInt(100)+1
        var number2 = Random().nextInt(100)+1
        Log.i("info",number1.toString() + " " + number2.toString())
        correctAnswerIndex = Random().nextInt(4) + 1
        correctAnswer = number1 + number2
        while(correctAnswer>100){
            number1 = Random().nextInt(100)+1
            number2 = Random().nextInt(100)+1
            correctAnswer = number1 + number2
        }
        (questionText as TextView).text = number1.toString() + " + " + number2.toString()
        var array : ArrayList<Int> = ArrayList()
        for(i in 0..3)
        {
            var temp = Random().nextInt(100)
            while(temp==correctAnswer)
                temp = Random().nextInt(100)
            array.add(temp)
            println(temp)
        }
        (button1 as TextView).text = array[0].toString()
        (button2 as TextView).text = array[1].toString()
        (button3 as TextView).text = array[2].toString()
        (button4 as TextView).text = array[3].toString()
        if(correctAnswerIndex==1)
            (button1 as Button).text = (number1+number2).toString()
        else if(correctAnswerIndex==2)
            (button2 as Button).text = (number1+number2).toString()
        else if(correctAnswerIndex==3)
            (button3 as Button).text = (number1+number2).toString()
        else if(correctAnswerIndex==4)
            (button4 as Button).text = (number1+number2).toString()
        totalquestion = totalquestion + 1
    }
    fun answerFunction(view: View)
    {
        if(view.tag==(correctAnswerIndex).toString()) {
            (answerText as TextView).text = "Correct"
            rightAnswer = rightAnswer + 1
        }
        else
            (answerText as TextView).text = "Wrong"
        (resultText as TextView).text = rightAnswer.toString() + "/" + totalquestion.toString()
        if(count>0)
            generate()
    }
    fun finished()
    {
        (answerText as TextView).text = "You Answered " + rightAnswer.toString() + " Out of " + totalquestion.toString() + " Correctly"
        button1.isEnabled = false
        button2.isEnabled = false
        button3.isEnabled = false
        button4.isEnabled = false
        reset.visibility = View.VISIBLE
    }
    fun clickFunction(view: View)
    {
        count = 30
        (timerText as TextView).text = "30s"
        (resultText as TextView).text = "0/1"
        rightAnswer = 0
        totalquestion = 1
        correctAnswer = -1
        correctAnswerIndex = -1
        button1.isEnabled = true
        button2.isEnabled = true
        button3.isEnabled = true
        button4.isEnabled = true
        reset.visibility = View.INVISIBLE
        (answerText as TextView).text = " "
        start(view)
    }
}
