package com.example.evchargingstationfinder

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.evchargingstationfinder.ui.theme.EVChargingStationFinderTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

// Define the StationData class with all required fields
data class StationData(
    val latLng: LatLng,
    val name: String,
    val chargingType: String,
    val numberOfPorts: Int,
    val connectorType: String,  // New field
    val phoneNumber: String,     // New field
    val availability: String,    // New field
    val address: String           // New field
)


class MainActivity : ComponentActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize mapView
        mapView = MapView(this).apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MainActivity) // Set the callback to this activity
        }

        setContent {
            EVChargingStationFinderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MapViewContainer(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Set the default location to Indore and add markers for EV charging stations
        val indore = LatLng(22.7196, 75.8577)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(indore, 12f))

        // Add EV charging station markers
        val stations = listOf(
            StationData(
                LatLng(22.728211087844986, 75.86947832625945),
                "Electric Vehicle Charging Station",
                "Fast Charging",
                2,
                "Unknown Connector - 250kW",
                "NA",
                "Unknown",
                "PVG9+V2P, Near Rajkumar Bridge, Snehlataganj, Indore, Madhya Pradesh 452001"
            ),
            StationData(
                LatLng(22.729730251092718, 75.86309533513526),
                "Electric Vehicle Charging Station",
                "Fast Charging",
                1,
                "Unknown",
                "08002546735",
                "24 Hours",
                "PVH7+H9F, Jail Rd, Indore GPO, Indore, Madhya Pradesh 452001"
            ),
            StationData(
                LatLng(22.737675349956408, 75.87078646058194),
                "Electric Vehicle Charging Station",
                "Fast Charging",
                3 ,
                "Wall socket - 10kW & 3.3kW",
                "NA",
                "24 Hours",
                "PVPC+P82, Malwa Mill Rd, Patni Pura, Indore, Madhya Pradesh 452001"
            ),
            StationData(
                LatLng(22.72376967662701, 75.88192962669673),
                "Ather Grid Charging Station",
                "Fast Charging",
                1 ,
                "LECCS - 1.1kW",
                "07676600900",
                "24 Hours",
                "Revolt service center, Mahatma Gandhi Rd, Near Indraprastha Tower, Race Course Road, Indore, Madhya Pradesh 452001"
            ),
            StationData(
                LatLng(22.71172382821245, 75.85788008705701),
                "Ather Grid Charging Station",
                "Fast Charging",
                1 ,
                "LECCS - 1.1kW",
                "07676600900",
                "24 Hours",
                "Priyanka Enterprises, 5/2, Moti Tabela, Indore, Madhya Pradesh 452007"
            ),

            StationData(
                LatLng(22.75505680121905, 75.886324103934),
                "Ather Grid Charging Station",
                "Fast Charging",
                1 ,
                "LECCS - 1.1kW",
                "07676600900",
                "24 Hours",
                "FH-228, Sceme No 54, Vijay Nagar, Indore, Madhya Pradesh 452010"
            ),
            StationData(
                LatLng(22.721034357023562, 75.84955849447441),
                "Electric Vehicle Charging Station",
                "Fast Charging",
                1 ,
                "Unknown",
                "NA",
                "24 Hours",
                "Laxmi Building, Police Station, 22, North, Yashwant Ganj, Malharganj, Indore, Madhya Pradesh 452002"
            ),
            StationData(
                LatLng(22.7153145950775, 75.88790954690678),
                "EV Urjaa Charging Station",
                "Fast Charging",
                1 ,
                "Unknown",
                "NA",
                "10am-8:30pm",
                "EV Urjaa 2nd Floor, ICCC Building, Indore Smart Seed Incubation Centre AICTSL Campus, Chartered Bus Stand, near Geeta Bhawan, Indore, Madhya Pradesh 452001"
            ),
            StationData(
                LatLng(22.731028303401565, 75.89152092516511),
                "Tata Power  Charging Station",
                "Fast Charging",
                2 ,
                "Unknown",
                "NA",
                "24 Hours",
                "2, AB Rd, Shree Nagar Main Colony, Anoop Nagar, Indore, Madhya Pradesh 452001"
            ),
            StationData(
                LatLng(22.719201554973896, 75.87296354894895),
                "Jio-bp pulse Charging Station",
                "Fast Charging",
                2 ,
                "Unknown",
                "18008919023",
                "24 Hours",
                "Nexus Treasure Island, opposite Ravindra Natya Grah, Flim Colony, South Tukoganj, Indore, Madhya Pradesh 452001"
            ),
            StationData(
                LatLng(22.75537340170432, 75.8860236965166),
                "Ather Grid Charging Station",
                "Fast Charging",
                1 ,
                "Unknown",
                "NA",
                "24 Hours",
                "FH-228, Sceme No 54, Vijay Nagar, Indore, Madhya Pradesh 452010"
            ),

            StationData(
                LatLng(22.75365766403533, 75.88373928858869),
                "Charge Zone Charging Station",
                "Fast Charging",
                2 ,
                "Unknown",
                "18001212025",
                "24 Hours",
                "H-2, Marriott Hotel, Maguda Nagar, Scheme No 54, Indore, Madhya Pradesh 452010"
            ),
            StationData(
                LatLng(22.72142920976412, 75.87932145789797),
                "Nikol Automotive Private Limited Charging Station",
                "Fast Charging",
                2 ,
                "Unknown",
                "18001212025",
                "24 Hours",
                "PVCH+5P3, South Tukoganj, Indore, Madhya Pradesh 452001"
            ),

            StationData(
                LatLng(22.72402923142314, 75.87820611089211),
                "Ather Grid Charging Station",
                "Fast Charging",
                2 ,
                "LECCS - 1.1kW",
                "07676600900",
                "24 Hours",
                "Infront of, 580, Mahatma Gandhi Rd, near Indraprastha Tower, Race Course Road, Indore, Madhya Pradesh 452001"
            ),

            StationData(
                LatLng(22.72402923142314, 75.87820611089211),
                "Ather Grid Charging Station",
                "Fast Charging",
                1 ,
                "LECCS - 1.1kW",
                "07676600900",
                "24 Hours",
                "Dosa Magic, 147 B, Annapurna Rd, near Annapurna Police Station, Moon Palace Colony, Indore, Madhya Pradesh 452009"
            ),
            StationData(
                LatLng(22.71837077309489, 75.88168256582593),
                "Nikol Automotive Private Limited Charging Station",
                "Fast Charging",
                1 ,
                "Type 2\n" +
                        "· 7.4 kW",
                "NA",
                "24 Hours",
                "Treebo Trend, omni palace hotel, Ratlam Kothi Main Rd, Ratlam Kothi, Indore, Madhya Pradesh 452001"
            ),
            StationData(
                LatLng(22.743843276606345, 75.885831787057),
                "Tata Power Charging Station",
                "Fast Charging",
                1 ,
                "Unknown",
                "NA",
                "24 Hours",
                "Near 452010, Patni Pura Rd, Nanda Nagar, LIG Main Rd, LIG Colony, Indore, Madhya Pradesh 452010"
            ),
            StationData(
                LatLng(22.71128456981706, 75.9068504712012),
                "EV COSMOS Charging Station",
                "Fast Charging",
                1 ,
                "Unknown",
                "09981032747",
                "24 Hours",
                "Hotel Essentia, near world cup square, Vandana Nagar, Indore, Madhya Pradesh 452015"
            ),
            StationData(
                LatLng(22.713211647452884, 75.84068547861857),
                "Ather Grid Charging Station",
                "Fast Charging",
                1 ,
                "LECCS - 1.1 kW",
                "NA",
                "24 Hours",
                "PR7Q+36W, Dhar Rd, Labriya Bheru, Raj Mohalla, Indore, Madhya Pradesh 452002"
            ),

            StationData(
                LatLng(22.713211647452884, 75.84068547861857),
                "Ather Grid Charging Station",
                "Fast Charging",
                1 ,
                "LECCS - 1.1 kW",
                "NA",
                "24 Hours",
                "PR7Q+36W, Dhar Rd, Labriya Bheru, Raj Mohalla, Indore, Madhya Pradesh 452002"
            ),


        // Add more stations as needed...
        )

        // Add EV charging station markers
        stations.forEach { station ->
            val marker = googleMap.addMarker(MarkerOptions().position(station.latLng).title(station.name))
            marker?.tag = station // Store the station data in the marker's tag
        }

        // Set custom info window adapter
        googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            // This method returns the contents of the info window
            override fun getInfoContents(marker: Marker): View? {
                val station = marker.tag as? StationData ?: return null // Use safe cast to get StationData

                // Inflate your custom layout for the info window
                val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.custom_info_window, null)

                // Populate the info window with data
                view.findViewById<TextView>(R.id.title).text = station.name
                view.findViewById<TextView>(R.id.chargingType).text = "Charging Type: ${station.chargingType}"
                view.findViewById<TextView>(R.id.numberOfPorts).text = "Number of Ports: ${station.numberOfPorts}"
                view.findViewById<TextView>(R.id.phoneNumber).text = "Phone: ${station.phoneNumber}"
                view.findViewById<TextView>(R.id.availability).text = "Availability: ${station.availability}"
                view.findViewById<TextView>(R.id.connectorType).text = "Connector Type: ${station.connectorType}"
                view.findViewById<TextView>(R.id.address).text = "Address: ${station.address}"


                return view
            }

            // This method returns null to use the default info window frame
            override fun getInfoWindow(marker: Marker): View? {
                return null
            }
        })
        googleMap.setOnInfoWindowClickListener { marker ->
            val station = marker.tag as? StationData
            station?.let {
                // Show a dialog to confirm before opening Google Maps
                AlertDialog.Builder(this)
                    .setTitle("Navigate to ${station.name}")
                    .setMessage("Do you want to get directions to this charging station?")
                    .setPositiveButton("Yes") { _, _ ->
                        openGoogleMapsDirections(it.latLng)
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        }

    }
    private fun openGoogleMapsDirections(latLng: LatLng) {
        val uri = "google.navigation:q=${latLng.latitude},${latLng.longitude}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps") // Ensure that the intent opens in Google Maps
        startActivity(intent)
    }


    @Composable
    fun MapViewContainer(modifier: Modifier = Modifier) {
        // Use remember to keep track of MapView
        AndroidView(
            factory = { mapView },
            modifier = modifier.fillMaxSize()
        )
    }

    // Handle MapView lifecycle
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
