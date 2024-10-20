package com.example.alamiya_task.presentation.qiblaDirection

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.widget.Toast
import com.example.alamiya_task.R
import com.example.alamiya_task.core.helper_classes.LocationHelper
import com.example.alamiya_task.databinding.FragmentQiblaBinding
import org.osmdroid.api.IMapController
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
class MapHelper {

    @SuppressLint("UseCompatLoadingForDrawables")
    fun createMarkerOnTheMap(resourceId: Int, lat: Double, long: Double , map : MapView , resources : android.content.res.Resources) {
        val m = Marker(map)
        m.position = GeoPoint(lat, long)
        m.icon = resources.getDrawable(resourceId)
        m.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_TOP)
        map.overlays.add(m)
    }

    fun animateZoom(startZoom: Double, endZoom: Double, durationMillis: Long , mapController : IMapController) {
        val zoomDifference = kotlin.math.abs(startZoom - endZoom)
        val zoomPerMillis = zoomDifference / durationMillis

        CoroutineScope(Dispatchers.Main).launch {
            val startTime = System.currentTimeMillis()
            var currentZoom = startZoom
            while (currentZoom < endZoom) {
                val elapsedMillis = System.currentTimeMillis() - startTime
                currentZoom = startZoom + (zoomPerMillis * elapsedMillis)
                mapController.setZoom(currentZoom)
                delay(16) // Delay for smooth animation (approximately 60 FPS)
            }
            mapController.setZoom(endZoom) // Ensure final zoom is set accurately
        }
    }

    fun drawPolyLine(startPoint: GeoPoint, endPoint: GeoPoint , line: Polyline , map: MapView) {
        val geoPoints: MutableList<GeoPoint> = ArrayList()
        geoPoints.add(startPoint)
        geoPoints.add(endPoint)
        line.setPoints(geoPoints)
        line.setOnClickListener { polyline, mapView, _ ->
            Toast.makeText(
                mapView.context,
                "Polyline with " + polyline.points.size + " Qibla Direction",
                Toast.LENGTH_LONG
            ).show()
            false
        }
        map.overlayManager.add(line)
    }

    fun rotateCompass(azimuth: Float , binding : FragmentQiblaBinding) {
        val rotation = -(azimuth - 90f) // Adjust the rotation to start from 90 degrees
        binding.compass.rotation = rotation
    }

    fun fetchLocation(locationHelper: LocationHelper, mapController : IMapController, viewModel : QiblaViewModel, map : MapView, line: Polyline, resources : android.content.res.Resources, context : Context) {
        locationHelper.fetchLocation(object : LocationHelper.OnLocationFetchedListener {
            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onLocationFetched(location: Location?, address: String?) {
                // Animation will zoom in map
                animateZoom(3.5, 6.5, 1000 , mapController)
                if (location != null) {
                    viewModel.getQibla(location.latitude, location.longitude) // Use latitude and longitude

                    // Draw poly line
                    val startPoint = GeoPoint(location.latitude, location.longitude)
                    val endPoint = GeoPoint(21.4225, 39.8262)
                    mapController.setCenter(startPoint)
                    drawPolyLine(startPoint, endPoint , line, map )

                    // Place markers
                    createMarkerOnTheMap(R.drawable.baseline_location_on_24, location.latitude, location.longitude , map, resources,)
                    createMarkerOnTheMap(R.drawable.baseline_mosque_24, 21.4225, 39.8262 , map, resources)
                }
            }

            override fun onError(error: String?) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}
