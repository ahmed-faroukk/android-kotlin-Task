package com.example.alamiya_task.core.connectivity_observer

object NetworkState {

    @Volatile
    private var isOnline: Boolean = false

    fun setNetworkState(isOnline: Boolean) {
        synchronized(this) {
            NetworkState.isOnline = isOnline
        }
    }

    fun isOnline(): Boolean {
        synchronized(this) {
            return isOnline
        }
    }
}