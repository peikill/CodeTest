package com.example.myapplication.bean

data class ResponseData<out T>(
    val resultCount: Int,
    val results: T
)
