package com.example.shivam.setlocation

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager
    private var locationListener: LocationListener = object : LocationListener{
        override fun onLocationChanged(p0: Location?) {
            Log.i("Locationio",p0.toString())
            Toast.makeText(applicationContext,p0.toString(),Toast.LENGTH_LONG).show()
            mMap.clear()
            val myLocation = LatLng(p0!!.latitude, p0!!.longitude)
            mMap.addMarker(MarkerOptions().position(myLocation).title("Your Location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation,15f))
        }

        override fun onProviderDisabled(p0: String?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1) {
            if (grantResults.size > 1 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,0,0f,locationListener)
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(Build.VERSION.SDK_INT < 23){
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,0,0f,locationListener)
        }
        else{
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
            }
            else {
                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,0,0f,locationListener)
                mMap.clear()
                var last : Location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
                var latLng = LatLng(last.latitude,last.longitude)
                mMap.addMarker(MarkerOptions().position(latLng).title("Your Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))

            }
        }
        // Add a marker in Sydney and move the camera

    }
}
