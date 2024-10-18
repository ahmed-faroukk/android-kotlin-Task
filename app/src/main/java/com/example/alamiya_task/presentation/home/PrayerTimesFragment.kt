package com.example.alamiya_task.presentation.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.alamiya_task.common.util.LocationHelper
import com.example.alamiya_task.common.util.PermissionManager
import com.example.alamiya_task.common.util.formatAddress
import com.example.alamiya_task.databinding.FragmentPrayerTimesBinding
import com.example.alamiya_task.presentation.home.components.PermissionWidget
import com.example.alamiya_task.presentation.home.components.showError
import com.example.alamiya_task.presentation.home.components.showLoading
import com.example.alamiya_task.presentation.home.components.showPrayerTimesScreen
import com.example.alamiya_task.presentation.home.components.showSettings

@RequiresApi(Build.VERSION_CODES.O)
class PrayerTimesFragment : Fragment() {
    private lateinit var binding: FragmentPrayerTimesBinding
    private val viewModel: PrayerTimesViewModel by activityViewModels()
    private lateinit var permissionManager: PermissionManager
    private lateinit var locationHelper: LocationHelper


    override fun onResume() {
        super.onResume()
        // Re-check location permission when returning to the fragment
        checkLocationPermission()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrayerTimesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationHelper = LocationHelper(context = this.requireActivity() as AppCompatActivity)
        permissionManager = PermissionManager(requireActivity())
         checkLocationPermission()
        viewModel.state.observe(viewLifecycleOwner) { state ->
            initObservation()
        }
    }


    private fun initObservation() {
        val state = viewModel.state.value
        val location = viewModel.userLocation

        if (state?.isLoading == true) {
            showLoading(binding)
        }
        if (state?.error?.isNotEmpty() == true) {
            showError(retryAction = {
            }, binding )
        }
        state?.data?.let { showPrayerTimesScreen(it, location.value?.address?.formatAddress() ?: "not found", binding) }
    }


    private fun checkLocationPermission() {
        permissionManager.checkLocationPermission(
            onPermissionGranted = {
                viewModel.getUserLocation(locationHelper, binding) @Composable { PermissionWidget(permissionManager = permissionManager) }.also {
                }
            },
            onPermissionDenied = {
                showSettings(binding , permissionManager)
            }
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionManager.handlePermissionResult(requestCode, grantResults,
            onPermissionGranted = { initObservation() },
            onPermissionDenied = {    showSettings(binding , permissionManager) }
        )
    }


}
