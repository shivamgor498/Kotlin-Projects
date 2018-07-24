package com.example.shivam.get_location
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
class MainActivity : AppCompatActivity() {
    private lateinit var locationManager: LocationManager
    private var locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(p0: Location?) {
            Toast.makeText(applicationContext,p0.toString(),Toast.LENGTH_SHORT).show()
            Log.i("Location",p0.toString())
        }
        override fun onProviderDisabled(p0: String?) {
            Toast.makeText(applicationContext,p0.toString(),Toast.LENGTH_SHORT).show()
            Log.i("Location",p0.toString())
        }
        override fun onProviderEnabled(p0: String?) {
            Toast.makeText(applicationContext,p0.toString(),Toast.LENGTH_SHORT).show()
            Log.i("Location",p0.toString())
        }
        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            Toast.makeText(applicationContext,p0.toString(),Toast.LENGTH_SHORT).show()
            Log.i("Location",p0.toString())
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0f, locationListener)
            else
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        }
    }
    fun clickFunction(view : View){
        Toast.makeText(applicationContext,"Hi",Toast.LENGTH_SHORT).show()
        if(ContextCompat.checkSelfPermission(applicationContext,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0f,locationListener)
        else
            Toast.makeText(applicationContext,"error",Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0f, locationListener)
        }
    }
}