package com.example.travelplan.Search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.travelplan.R
import com.example.travelplan.databinding.ActivitySearchDestinationBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse

class SearchDestinationActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var binding: ActivitySearchDestinationBinding
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.searchBtn.setOnClickListener {
            val searchText = binding.searchEt.text.toString()

            Places.initializeWithNewPlacesApiEnabled(applicationContext, "AIzaSyC5LQJO0WLpJ_pWFAesDMugTgzCvHhzkNM")
            val placesClient = Places.createClient(this)
            val request = FindAutocompletePredictionsRequest.builder().setQuery(searchText).build()

            placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener { response: FindAutocompletePredictionsResponse -> // 서버의 응답을 받는 콜백
                    val prediction = response.autocompletePredictions[0]

                    val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
                    val placeRequest = FetchPlaceRequest.newInstance(prediction.placeId, placeFields)

                    placesClient.fetchPlace(placeRequest)
                        .addOnSuccessListener { placeResponse: FetchPlaceResponse ->
                            val place = placeResponse.place
                            val destination = LatLng(place.latLng.latitude, place.latLng.longitude)
                            mMap.clear()
                            mMap.addMarker(
                                MarkerOptions()
                                    .position(destination)
                                    .title(place.name)
                            )
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(destination))
                        }
                        .addOnFailureListener { placeException: Exception ->
                            if (placeException is ApiException) {
                                Log.d("@@@", placeException.message.toString())
                            }
                        }
                }
                .addOnFailureListener { exception: Exception? ->
                    if (exception is ApiException) {
                        Log.d("@@@ error @@@", exception.statusCode.toString())
                    }
                }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
