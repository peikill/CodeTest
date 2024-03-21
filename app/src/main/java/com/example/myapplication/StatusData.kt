package com.example.myapplication

import com.example.myapplication.bean.MusicBean

sealed class StatusData {
    data class Musice(val music: List<MusicBean>) : StatusData()
    data class Error(val error: String? = "") : StatusData()
    data class Empty(val error: String? = "") : StatusData()
}