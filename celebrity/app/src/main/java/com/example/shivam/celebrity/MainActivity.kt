package com.example.shivam.celebrity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    var celebURL : ArrayList<String> = arrayListOf()
    var celebname : ArrayList<String> = arrayListOf()
    var buttonArray : Array<Int> = arrayOf(-1,-1,-1,-1)
    var chosenceleb : Int = 0
    var chosenIndex : Int = 0
    lateinit var image : ImageView
    lateinit var button1 : Button
    lateinit var button2 : Button
    lateinit var button3 : Button
    lateinit var button4 : Button
    inner class DownloadImage(var imageView: ImageView) : AsyncTask<String,Unit,Bitmap>() {
        override fun doInBackground(vararg p0: String?): Bitmap? {
            try {
                var inp: InputStream = URL(p0[0]).openStream()
                return BitmapFactory.decodeStream(inp)
            }
            catch (e:Exception){
                Log.e("Error",e.message)
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }
    inner class DownloadTask : AsyncTask<String,Unit,String>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String): String {
            var result = ""
            var url : URL
            var urlConnection : HttpURLConnection
            try {
                url = URL(p0[0])
                urlConnection = url.openConnection() as HttpURLConnection
                var inp: InputStream = urlConnection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(inp))
                var line : String? = bufferedReader.readLine()
                while(line!=null) {
                    result+=line
                    line = bufferedReader.readLine()
                }
                var splitresult = result.split("<div class=\"sidebarInnerContainer\">")
                var p : Pattern = Pattern.compile("img src=\"(.*?)\"")
                var m : Matcher = p.matcher(splitresult[0])
                while (m.find())
                    celebURL.add(m.group(1))
                p = Pattern.compile("alt=\"(.*?)\"")
                m = p.matcher(splitresult[0])
                while (m.find())
                    celebname.add(m.group(1))
                chosenceleb = Random().nextInt(celebname.size)
                return result
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            return "-1"

        }

        override fun onProgressUpdate(vararg values: Unit?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

    }
    fun clickFunction(view : View){
        if(view.tag.toString().toInt()==chosenIndex)
            Toast.makeText(applicationContext,"Correct",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(applicationContext,"Incorrect",Toast.LENGTH_SHORT).show()
        for (i in 0..3)
            buttonArray[i] = -1
        chosenceleb = Random().nextInt(celebname.size)
        chosenIndex = Random().nextInt(4)
        DownloadImage(image).execute(celebURL[chosenceleb]).get()
        println(chosenIndex)
        println(celebname.size.toString() + " " + celebURL.size.toString() )
        for(i in 0..3)
        {
            if(i==chosenIndex)
            {
                if(i==0) {
                    button1.text = celebname[chosenceleb]
                }
                else if(i==1)
                    button2.text = celebname[chosenceleb]
                else if(i==2)
                    button3.text = celebname[chosenceleb]
                else if(i==3)
                    button4.text = celebname[chosenceleb]
                buttonArray[i] = chosenceleb
            }
            else{
                var rand = Random().nextInt(celebname.size)
                for (i in 0..3) {
                    while (rand == chosenceleb || rand==buttonArray[i])
                        rand = Random().nextInt(celebname.size)
                }
                if(i==0) {
                    button1.text = celebname[rand]
                }
                else if(i==1)
                    button2.text = celebname[rand]
                else if(i==2)
                    button3.text = celebname[rand]
                else if(i==3)
                    button4.text = celebname[rand]
                buttonArray[i] = rand
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("shivama","shivama")
        var result = DownloadTask().execute("http://www.posh24.se/kandisar").get()
        image = findViewById(R.id.celebrityImage)
        chosenIndex = Random().nextInt(4)
        button1 = findViewById(R.id.button0)
        button2 = findViewById(R.id.button1)
        button3 = findViewById(R.id.button2)
        button4 = findViewById(R.id.button3)
        DownloadImage(image).execute(celebURL[chosenceleb]).get()
        println(chosenIndex)
        for(i in 0..3)
        {
            if(i==chosenIndex)
            {
                if(i==0)
                    button1.text = celebname[chosenceleb]
                else if(i==1)
                    button2.text = celebname[chosenceleb]
                else if(i==2)
                    button3.text = celebname[chosenceleb]
                else if(i==3)
                    button4.text = celebname[chosenceleb]
                buttonArray[i] = chosenceleb
            }
            else{
                var rand = Random().nextInt(celebname.size)
                for(i in 0..3) {
                    while (rand == chosenceleb || rand==buttonArray[i])
                        rand = Random().nextInt(celebname.size)
                }
                if(i==0)
                    button1.text = celebname[rand]
                else if(i==1)
                    button2.text = celebname[rand]
                else if(i==2)
                    button3.text = celebname[rand]
                else if(i==3)
                    button4.text = celebname[rand]
                buttonArray[i] = rand
            }
        }
    }
}
