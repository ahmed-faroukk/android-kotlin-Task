package com.example.alamiya_task

import com.example.alamiya_task.core.components.ConnectivityHandler
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContextCompat
import com.example.alamiya_task.common.connectivityObserver.ConnectivityObserver
import com.example.alamiya_task.common.connectivityObserver.NetworkConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        findViewById<ComposeView>(R.id.compose_view).setContent {
            NetworkObserver()
        }
    }

    @Composable
    fun NetworkObserver() {
        Surface(color = MaterialTheme.colorScheme.background) {
            val connectivityStatus = connectivityObserver.observe().collectAsState(
                initial = ConnectivityObserver.Status.Available
            )
            val infoDialogVisible = remember { mutableStateOf(true) }
            val firstLostConnection = remember { mutableStateOf(true) }
            ConnectivityHandler(connectivityStatus.value, infoDialogVisible, firstLostConnection)
        }

    }

}

