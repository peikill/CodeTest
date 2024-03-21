package com.example.myapplication

import com.example.myapplication.bean.MusicBean
import com.example.myapplication.bean.ResponseData
import retrofit2.http.GET

interface MainApi {
    //数量暂时写死
    @GET("search?term=歌&limit=20&country=HK")
    suspend fun getMusic(): ResponseData<List<MusicBean>>

}
