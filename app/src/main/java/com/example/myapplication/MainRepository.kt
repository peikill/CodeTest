package com.example.module_login.repository

import com.example.myapplication.MainApi
import com.example.myapplication.RetrofitClient
import com.example.myapplication.bean.MusicBean
import com.example.myapplication.bean.ResponseData


class MainRepository {

    private val mApi by lazy {
         RetrofitClient.mClient.create(MainApi::class.java)
    }

    suspend fun getMusics() : ResponseData<List<MusicBean>> {
        return mApi.getMusic()
    }


}







