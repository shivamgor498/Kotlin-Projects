package com.example.shivam.weather

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var cityName : EditText
    private lateinit var finalAnswer: TextView
    private lateinit var scontext : Context
    fun clickFunction(view : View){
        var mgr : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(cityName.windowToken,0)
        try {
            var encity = URLEncoder.encode(cityName.text.toString(), "UTF-8")
            DownloadTask(finalAnswer,scontext).execute("https://samples.openweathermap.org/data/2.5/weather?q=" + encity.toString() + "&appid=f6971609125266327c256075d8c2ae9e").get()
            Log.i("city name",cityName.text.toString())
        }
        catch (e: Exception){
            Toast.makeText(scontext,"City name cannot be found",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cityName = findViewById(R.id.city)
        finalAnswer = findViewById(R.id.waetherResult)
        scontext = applicationContext
    }
    class DownloadTask(var texta: TextView,var scontext : Context) : AsyncTask<String,Unit,String>(){
        override fun doInBackground(vararg p0: String?): String {
            var result = ""
            var url : URL
            var httpURLConnection : HttpURLConnection
            try{
                url = URL(p0[0])
                httpURLConnection = url.openConnection() as HttpURLConnection
                var inp : InputStream = httpURLConnection.inputStream
                var bufferedReader = BufferedReader(InputStreamReader(inp))
                var line : String? = bufferedReader.readLine()
                while(line!=null)
                {
                    result += line
                    line = bufferedReader.readLine()
                }
                Log.i("result",result)
                return result
            }
            catch (e : Exception) {
                Toast.makeText(scontext,"Weather could not be found",Toast.LENGTH_SHORT).show()
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var message = ""
            try {
                var jsonObject = JSONObject(result)
                var weather : String = jsonObject.getString("weather")
                Log.i("weather content",weather)
                var arr = JSONArray(weather)
                var sz = arr.length()-1
                for( i in 0..sz){
                    var jsonChild = arr.getJSONObject(i)
                    var main = jsonChild.getString("main").toString()
                    var description = jsonChild.getString("description").toString()
                    if(main!="" && description!="")
                        message += "$main : $description \r\n"
                    Log.i("main",jsonChild.getString("main"))
                    Log.i("description",jsonChild.getString("description"))
                    Log.i("message",message)
                }
                if(message!="")
                    texta.text = message
                else
                    Toast.makeText(scontext,"Weather could not be found",Toast.LENGTH_SHORT).show()
            }
            catch (e : Exception){
                Toast.makeText(scontext,"Weather could not be found",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
