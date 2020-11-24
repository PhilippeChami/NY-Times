package com.nytimes.android.core.util

import com.nytimes.android.core.vo.Resource
import com.nytimes.android.utils.GlobalFunctions
import retrofit2.HttpException
import javax.inject.Inject

open class ResponseHandler @Inject constructor() {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleSuccessWithLoading(data: T): Resource<T> {
        return Resource.successWithLoading(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        GlobalFunctions.printException(e)
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            401 -> "Unauthorized"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}