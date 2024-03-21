package com.example.myapplication.tool

import android.accounts.NetworkErrorException
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * 异常工具类
 */
object ExceptionUtil {

    /**
     * 处理异常，toast提示错误信息
     */
    fun catchException(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                catchHttpException(e.code())
            }
            is SocketTimeoutException -> {
                showToast("UnknownHostException")
            }
            is UnknownHostException, is NetworkErrorException -> {
                showToast("UnknownHostException")
            }
            is MalformedJsonException, is JsonSyntaxException -> {
                showToast("MalformedJsonException")
            }
            is InterruptedIOException -> {
                showToast("InterruptedIOException")
            }
//            is ApiException -> {
//                showToast(e.message?:"", e.code)
//            }
            is ConnectException -> {
                showToast( "ConnectException" )
            }
            else -> {
                showToast("something error")
            }
        }
    }

    /**
     * 处理网络异常
     */
    fun catchHttpException(errorCode: Int) {
        if (errorCode in 200 until 300) return
        showToast(
            catchHttpExceptionCode(
                errorCode
            ), errorCode
        )
    }

    /**
     * toast提示
     */
    private fun showToast(errorMsg: String, errorCode: Int = -1) {
        if (errorCode == -1) {
            ToastUtils.showShort(errorMsg)
        } else {
            ToastUtils.showShort("$errorCode：$errorMsg")
        }
    }

    /**
     * 处理网络异常
     */
    private fun catchHttpExceptionCode(errorCode: Int): String = when (errorCode) {
        in 500..600 -> "server error"
        in 400 until 500 -> "request error"
        else -> "request error"
    }
}

