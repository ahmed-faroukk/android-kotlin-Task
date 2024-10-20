package com.example.alamiya_task.presentation.qiblaDirection

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import com.example.alamiya_task.R
import com.example.alamiya_task.core.extentions.compassHandler
import com.example.alamiya_task.core.helper_classes.LocationHelper
import com.example.alamiya_task.databinding.FragmentQiblaBinding
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration.getInstance
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline

@SuppressLint("SourceLockedOrientationActivity")
@AndroidEntryPoint
class QiblaFragment : Fragment(R.layout.fragment_qibla), SensorEventListener {

    private lateinit var binding: FragmentQiblaBinding
    private lateinit var map: MapView
    private lateinit var locationHelper: LocationHelper
    private lateinit var mapController: IMapController
    private val viewModel: QiblaViewModel by activityViewModels()
    private var qiblaDirection: Double = 0.0
    private lateinit var sensorManager: SensorManager
    private var rotationVectorSensor: Sensor? = null
    private val line = Polyline()
    private val mapHelper = MapHelper()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = FragmentQiblaBinding.inflate(inflater, container, false)
        map = binding.map
        // Inflate and create the map
        map.setTileSource(TileSourceFactory.MAPNIK)
        mapController = map.controller
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInstance().load(
            requireContext(),
            PreferenceManager.getDefaultSharedPreferences(requireContext())
        )
        locationHelper = LocationHelper(requireActivity() as AppCompatActivity)
        mapController.setZoom(3.5)
        // Automatically fetch the location when the fragment is created
        mapHelper.fetchLocation(
            locationHelper,
            mapController,
            viewModel,
            map,
            line,
            resources,
            requireContext()
        )
        initObservation()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
        rotationVectorSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ROTATION_VECTOR) {
            val rotationMatrix = FloatArray(9)
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            val orientation = FloatArray(3)
            SensorManager.getOrientation(rotationMatrix, orientation)
            val azimuthInRadians = orientation[0]
            val azimuthInDegrees = Math.toDegrees(azimuthInRadians.toDouble()).toFloat()
            if (qiblaDirection != 0.0) binding.compass.compassHandler(
                azimuthInDegrees,
                qiblaDirection
            )
            mapHelper.rotateCompass(azimuthInDegrees, binding)
        }
    }


    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Handle accuracy changes if needed
    }


    private fun initObservation() {
        viewModel.state.observe(viewLifecycleOwner) { response ->
            if (response.error.isNotEmpty()) {
                Toast.makeText(requireContext(), response.error, Toast.LENGTH_SHORT).show()
            }
            if (response.data != null) {
                response.data.let {
                    qiblaDirection = it.data.direction
                }
            }

        }
    }
}
