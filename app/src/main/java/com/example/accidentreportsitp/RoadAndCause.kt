package com.example.accidentreportsitp


import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
//import kotlinx.android.synthetic.main.road_and_cause.*
import java.io.IOException

abstract class RoadAndCause : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapView: MapView
    private lateinit var helmetYes: CheckBox
    private lateinit var helmetNo: CheckBox
    private lateinit var seatbeltYes: CheckBox
    private lateinit var seatbeltNo: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.road_and_cause)

        // Initialize fusedLocationClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Initialize MapView
        mapView = findViewById(R.id.mapView2)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        // Initialize CheckBoxes
        helmetYes = findViewById(R.id.helmetYes)
        helmetNo = findViewById(R.id.helmetNo)
        seatbeltYes = findViewById(R.id.seatbeltYes)
        seatbeltNo = findViewById(R.id.seatbeltNo)

        // Setup CheckBox interactions if needed
        setupCheckBoxInteractions()
    }

    private fun setupCheckBoxInteractions() {
        // Ensure only one of the helmet checkboxes is checked
        helmetYes.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) helmetNo.isChecked = false
        }

        helmetNo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) helmetYes.isChecked = false
        }

        // Repeat for seatbeltYes and seatbeltNo with similar logic
        seatbeltYes.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) seatbeltNo.isChecked = false
        }

        seatbeltNo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) seatbeltYes.isChecked = false
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        if (p0 != null) {
            // Request location permission if not granted
            if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        LOCATION_PERMISSION_REQUEST_CODE
                )
            } else {
                // Permission already granted, get the current location
                getCurrentLocation(p0)
            }
        }
    }

    private fun getCurrentLocation(googleMap: GoogleMap) {
        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    // Use the location object to get latitude and longitude
                    val currentLatLng = LatLng(location.latitude, location.longitude)

                    // Optionally, convert LatLng to a location string
                    val address = getLocationAddress(location)

                    // Add a marker at the current location and move the camera
                    googleMap.addMarker(
                            MarkerOptions().position(currentLatLng).title(address)
                    )
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                }
            }
        }
    }

    private fun getLocationAddress(location: Location): String {
        val geocoder = Geocoder(this)
        var addressText = ""
        try {
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val address = addresses[0]
                addressText = address.getAddressLine(0) ?: "Unknown location"
            } else {
                addressText = "Unknown location"
            }
        } catch (e: IOException) {
            e.printStackTrace()
            addressText = "Unknown location"
        }
        return addressText
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, get the current location
                mapView.getMapAsync(this)
            } else {
                // Permission denied, show a message or handle it accordingly
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}
