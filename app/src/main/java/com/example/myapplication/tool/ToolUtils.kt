package com.example.myapplication.tool

import android.util.Log

const val TAG = "utils"

//示例：testCode(Pair(1, 9), Pair(78, 90), Pair(7, 10))
fun testCode(vararg intervals : Pair<Int, Int>) : Boolean {
    val set = HashSet<Int>()
    intervals.forEach {
        val fir = it.first
        val sec = it.second
        if (fir >= sec) {
            Log.e(TAG, "input params error")
            return false
        }
        for(i in fir .. sec - 1) {
            if (set.contains(i)) {
                return false
            } else {
                set.add(i)
            }
        }
    }
    return true
}
