package com.example.alamiya_task.core.state_handler

sealed class Resource<T>(val data: T ? = null , val message :String ? = null ){

    class Success<T>(data : T) : Resource<T>(data)
    class Error<T>(message: String? , data: T?) : Resource<T>(data ,message)
    class Loading<T> : Resource<T>()

}
