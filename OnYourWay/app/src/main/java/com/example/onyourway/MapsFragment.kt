package com.example.onyourway

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.provider.SettingsSlicesContract.KEY_LOCATION
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.onyourway.databinding.FragmentMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    private var MainActivity : MainActivity = MainActivity()
    private var locationPermissionGranted = true
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val defaultLocation = LatLng(40.6442700, -8.6455400)

    private var lastKnownLocation: Location? = null

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION)
        }
        val binding = DataBindingUtil.inflate<FragmentMapsBinding>(inflater,
            R.layout.fragment_maps,container,false)
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        // Async map
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this@MapsFragment.activity!!)
        getDeviceLocation()
        // Async map
        supportMapFragment!!.getMapAsync { googleMap ->
            // When map is loaded
            mMap=googleMap
            googleMap.setOnMapClickListener { latLng -> // When clicked on map
                // Initialize marker options
                val markerOptions = MarkerOptions()
                // Set position of marker
                markerOptions.position(latLng)
                // Set title of marker
                markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)
                // Remove all marker
                // Animating to zoom the marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                // Add marker on map
                googleMap.addMarker(markerOptions)

            }

        }
        return binding.root    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     *
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        Log.d("rats","qui")
        mMap = googleMap
        val markerOptions = MarkerOptions()
        // Set position of marker
        markerOptions.position(defaultLocation)
        // Set title of marker
        markerOptions.title(defaultLocation.latitude.toString() + " : " + defaultLocation.longitude)
        // Remove all marker
        // Animating to zoom the marker
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))
        // Add marker on map
        mMap!!.addMarker(markerOptions)
        getDeviceLocation()

    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {

            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(MainActivity) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            mMap?.moveCamera(CameraUpdateFactory.newLatLng(
                                LatLng(lastKnownLocation!!.latitude,
                                    lastKnownLocation!!.longitude)))
                            Log.e("Mapper", lastKnownLocation!!.latitude.toString())
                            Log.e("Mapper", lastKnownLocation!!.longitude.toString())
                            val currlatLng = LatLng(lastKnownLocation!!.latitude, lastKnownLocation!!.longitude)
                            Log.e("Mapper", mMap.toString())

                            val markerOptions = MarkerOptions().position(currlatLng)
                            // Set position of marker
                            // Set title of marker
                            markerOptions.title(lastKnownLocation!!.latitude.toString() + " : " + lastKnownLocation!!.longitude)
                            // Remove all marker
                            // Animating to zoom the marker
                            mMap?.clear()
                            // Add marker on map
                            mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(currlatLng, 10f))


                            mMap?.addMarker(markerOptions)
                        }
                    } else {
                        Log.d("Maper", "Current location is null. Using defaults.")
                        Log.e("Maper", "Exception: %s", task.exception)
                        mMap?.moveCamera(CameraUpdateFactory
                            .newLatLng(defaultLocation))
                        mMap?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

}
