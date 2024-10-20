package com.example.alamiya_task.presentation.home.components

import PermissionWidget
import ShimmerList
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.alamiya_task.core.helper_classes.PermissionManager
import com.example.alamiya_task.databinding.FragmentPrayerTimesBinding
import com.example.alamiya_task.domin.entity.prayer_time.PrayerTimeResponse

@RequiresApi(Build.VERSION_CODES.O)
fun showPrayerTimesScreen(
    prayerTimeResponse: PrayerTimeResponse,
    address: String,
    binding: FragmentPrayerTimesBinding,
    onButtonClick: () -> Unit
) {
    binding.myComposable.setContent {
        PrayerTimesScreen(prayerTimeResponse = prayerTimeResponse, address = address, onButtonClick)
    }
}

fun showLoading(binding: FragmentPrayerTimesBinding) {
    binding.myComposable.setContent {
        ShimmerList()
    }
}

fun showError(retryAction: () -> Unit, binding: FragmentPrayerTimesBinding, msg: String) {
    binding.myComposable.setContent {
        ErrorWidget(msg = msg) {
            retryAction()
        }
    }
}

fun showSettings(binding: FragmentPrayerTimesBinding, permissionManager: PermissionManager) {
    binding.myComposable.setContent {
        PermissionWidget(permissionManager = permissionManager)
    }

}
