package com.example.shivam.hikerswatch

import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var locationManager: LocationManager
    private var locationListener: LocationListener = object : LocationListener{
        override fun onLocationChanged(p0: Location?) {
            updateLocationInfo(p0)
        }

        override fun onProviderDisabled(p0: String?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }
    }
    private lateinit var latTextView: TextView
    private lateinit var lonTextView: TextView
    private lateinit var accTextView: TextView
    private lateinit var altTextView: TextView
    private lateinit var addTextView: TextView
    fun updateLocationInfo(location: Location?){
        Log.i("HikersLocaiton",location.toString())
        latTextView.text = "Latitude : " + location?.latitude
        lonTextView.text = "Longitude : " + location?.longitude
        accTextView.text = "Accuracy : " + location?.accuracy
        altTextView.text = "Altitude : " + location?.altitude
        var geocoder  = Geocoder(applicationContext, Locale.getDefault())
        var listAddress : List<Address> = geocoder.getFromLocation(location!!.latitude,location!!.longitude,1)
        var address  = "Address : "
        if(listAddress[0].subThoroughfare != null)
            address += listAddress[0].subThoroughfare + " "
        if(listAddress[0].thoroughfare != null)
            address += listAddress[0].thoroughfare + "\n"
        if(listAddress[0].locality != null)
            address += listAddress[0].locality + "\n"
        if(listAddress[0].postalCode != null)
            address += listAddress[0].postalCode + " "
        if(listAddress[0].countryName != null)
            address += listAddress[0].countryName + "\n"
        Log.i("Place",listAddress[0].toString())
        addTextView.text = address
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,0,0f,locationListener)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        latTextView = findViewById(R.id.latTextView)
        lonTextView = findViewById(R.id.lonTextView)
        accTextView = findViewById(R.id.accTextView)
        altTextView = findViewById(R.id.altTextView)
        addTextView = findViewById(R.id.addTextView)
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(Build.VERSION.SDK_INT < 23){
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,0,0f,locationListener)
        }
        else
        {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
            else {
                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0f, locationListener)
                var location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
                if(location!=null)
                    updateLocationInfo(location)
            }
        }
    }
}
