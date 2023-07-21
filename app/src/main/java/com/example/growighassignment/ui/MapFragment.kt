package com.example.growighassignment.ui

import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.growighassignment.R
import com.example.growighassignment.databinding.FragmentMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(R.layout.fragment_map) , OnMapReadyCallback
{

    private lateinit var mMap : GoogleMap
    private lateinit var binding : FragmentMapBinding
    private lateinit var currentLocation : Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode=101

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMapBinding.bind(view)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync {
            mMap=it
        }
        fusedLocationProviderClient=LocationServices.
        getFusedLocationProviderClient(requireContext())
        getCurrentlocation()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()

    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }
    private fun getCurrentlocation() {
        if(ActivityCompat.checkSelfPermission(
                requireContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION)!=
                    PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)!=
                    PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION),permissionCode)
            return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if(it!=null){
                currentLocation=it
                val laLng=LatLng(currentLocation.latitude,currentLocation.longitude)
                val markerOptions=MarkerOptions().position(laLng).title("current location ${it.latitude}, ${it.longitude}")
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(laLng,7f))
                mMap.addMarker(markerOptions)
            }
            else {
                Toast.makeText(requireContext(),"Turn on your location",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            permissionCode -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentlocation()
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        val laLng=LatLng(currentLocation.latitude,currentLocation.longitude)
        val markerOptions=MarkerOptions().position(laLng).title("current location")
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(laLng,7f))
        map.addMarker(markerOptions)
    }


}