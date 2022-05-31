package com.example.mymaster.presentations.Map

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.data.data.storage.SharedPrefs.SharedPrefsGeo
import com.example.mymaster.MAIN
import com.example.mymaster.R
import com.example.mymaster.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception
import java.util.*

private var _binding: FragmentMapsBinding? = null
private val binding get() = _binding!!
private lateinit var mMap: GoogleMap

private lateinit var locationManager: LocationManager
private lateinit var locationListener: LocationListener

class MapFragment : Fragment(), OnMapReadyCallback {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val mapFragment =
            getChildFragmentManager().findFragmentById(R.id.map_frag) as SupportMapFragment

        mapFragment.getMapAsync(this)

        return root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val chelaybinsk = LatLng(55.15, 61.4)
        mMap.setOnMapClickListener(listener)
        locationManager = MAIN.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        mMap.addMarker(MarkerOptions().position(chelaybinsk))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chelaybinsk, 15f))
        locationListener = LocationListener { location ->
            mMap.clear()
            val currentPosition = LatLng(location.latitude, location.longitude)
            mMap.addMarker(
                MarkerOptions().position(currentPosition).title("Вы здесь").draggable(true)
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 15f))
            val geocoder = Geocoder(MAIN, Locale.getDefault())
            try {
                val adress_list = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                if (adress_list.size > 0) {
                    val geo = SharedPrefsGeo()
                    geo.setAddress(adress_list[0].getAddressLine(0).toString())
                    geo.setGeo("${location.latitude}:${location.longitude}")

                    println(adress_list[0].getAddressLine(0).toString())
                }

            } catch (e: Exception) {

                e.printStackTrace()
            }

        }
        if (ContextCompat.checkSelfPermission(
                MAIN,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                MAIN,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )

        } else {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1,
                1f,
                locationListener
            )
            val lastKnowLocation =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastKnowLocation != null) {
                val lastLatlnh = LatLng(lastKnowLocation.latitude, lastKnowLocation.longitude)
                mMap.addMarker(MarkerOptions().position(lastLatlnh).title("Последнее место"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLatlnh, 15f))
            }
        }

    }

    /*
        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            if (requestCode == 1) {
                if (grantResults.size < 1) {
                    if (ContextCompat.checkSelfPermission(
                            MAIN,
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER, 1, 1f,
                            locationListener
                        )
                    }
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    */
    val listener = GoogleMap.OnMapClickListener { p0 ->
        mMap.clear()
        val geocoder = Geocoder(MAIN, Locale.getDefault())
        var address = ""
        try {
            val addressList = geocoder.getFromLocation(p0.latitude, p0.longitude, 1)
            if (addressList.size > 0) {
                if (addressList[0].thoroughfare != null) {
                    address = addressList[0].getAddressLine(0).toString()
                    val geo = SharedPrefsGeo()

                    geo.setAddress(address)
                    geo.setGeo("${p0.latitude}:${p0.longitude}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mMap.addMarker(MarkerOptions().position(p0).title(address).draggable(true))
    }
}