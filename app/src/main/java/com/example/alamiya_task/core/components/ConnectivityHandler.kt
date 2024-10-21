package com.example.alamiya_task.core.components
import CustomAppDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.alamiya_task.R
import com.example.alamiya_task.common.connectivityObserver.ConnectivityObserver

@Composable
fun ConnectivityHandler(
    status: ConnectivityObserver.Status,
    infoDialog: MutableState<Boolean>,
    firstLost: MutableState<Boolean>
) {
    when (status) {
        ConnectivityObserver.Status.Available -> {
            if (!firstLost.value) {
                CustomAppDialog(
                    title = "Back Online",
                    desc = "Your connection is back again",
                    imgId = R.drawable.outline_wifi_24,
                    onDismiss = {
                        infoDialog.value = true
                        firstLost.value = true
                    }
                )
            }
        }
        else -> {
            if (infoDialog.value) {
                CustomAppDialog(
                    title = "Whoops!",
                    desc = "No Internet Connection found.\nCheck your connection or try again.",
                    onDismiss = {
                        infoDialog.value = false
                        firstLost.value = false
                    }
                )
            }
        }
    }
}