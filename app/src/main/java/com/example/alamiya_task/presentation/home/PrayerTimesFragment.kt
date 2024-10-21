package com.example.alamiya_task.presentation.home

import com.example.alamiya_task.presentation.home.components.PermissionWidget
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
import androidx.navigation.fragment.findNavController
import com.example.alamiya_task.R
import com.example.alamiya_task.core.extentions.formatAddress
import com.example.alamiya_task.core.helper_classes.LocationHelper
import com.example.alamiya_task.core.helper_classes.PermissionManager
import com.example.alamiya_task.databinding.FragmentPrayerTimesBinding
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
       viewModel.checkLocationPermission(viewModel, binding, permissionManager, locationHelper)
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
        viewModel.checkLocationPermission(viewModel, binding, permissionManager, locationHelper)
        viewModel.prayerTimeState.observe(viewLifecycleOwner) {
            initObservation()
        }
    }


    private fun initObservation() {
        val state = viewModel.prayerTimeState.value
        val location = viewModel.userLocation

        if (state?.isLoading == true) {
            showLoading(binding)
        }

        if (state?.error?.isNotEmpty() == true) {
            showError(retryAction = {
                viewModel.getUserLocation(locationHelper, binding) @Composable {
                    PermissionWidget(
                        permissionManager = permissionManager
                    )
                }
            }, binding, state.error)
        }

        state?.data?.let {
            showPrayerTimesScreen(it, location.value?.address?.formatAddress() ?: "not found", binding
            ) {
                findNavController().navigate(R.id.action_prayerTimesFragment_to_qiblaFragment)
            }

        }
    }


    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionManager.handlePermissionResult(requestCode, grantResults,
            onPermissionGranted = { initObservation() },
            onPermissionDenied = {
                viewModel.checkLocationPermission(viewModel, binding, permissionManager, locationHelper)
                showSettings(binding, permissionManager) }
        )
    }



}
