package com.nytimes.android.utils

import android.content.Context
import android.widget.Toast

class GlobalFunctions {

    companion object{

        fun showToast(context:Context, msg:String, length:Int){
            Toast.makeText(context, msg, length).show()
        }

        fun printException(exception: Exception){
            exception.printStackTrace()
        }

        fun printException(throwable: Throwable){
            throwable.printStackTrace()
        }

    }
}