package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.module_login.repository.MainRepository
import com.example.myapplication.bean.MusicBean
import com.example.myapplication.tool.launch

class MainViewModel : ViewModel() {
    val musicStatus = MutableLiveData<StatusData>()

    var origionData = listOf<MusicBean>()
    val sortData = MutableLiveData<List<MusicBean>>()
    val filterData = MutableLiveData<List<MusicBean>>()

    private val mRepository by lazy {
        MainRepository()
    }

    fun getMusics() {
        launch({
            val data = mRepository.getMusics()
            origionData = data.results
            if (data.resultCount >= 0) {
                musicStatus.postValue(StatusData.Musice(data.results))
            } else {
                musicStatus.postValue(StatusData.Empty())
            }
        }, onError = { e: Throwable ->
            musicStatus.postValue(StatusData.Error())
        })
    }

    fun sortWithoutFilter() {
        if (origionData.isEmpty()) {
            return
        }
        val result = ArrayList(origionData)
        launch({
            val afterData = result.sortedBy {
                it.artistName
            }.sortedBy {
                it.trackPrice
            }
            sortData.postValue(afterData)
        })
    }

    fun getOriginMusic() {
        sortData.postValue(origionData)
    }

    fun filterMusic(input: String?, sort: Boolean) {
        if (origionData.isEmpty()) {
            return
        }
        if (input.isNullOrEmpty()) {
            if (sort) {
                sortWithoutFilter()
            } else {
                getOriginMusic()
            }
            return
        }
        val result = ArrayList(origionData)
        launch({
            var afterData = result.filter {
                it.artistName.contains(input) || it.trackName.contains(input)
            }
            if (sort) {
                afterData = afterData.sortedBy {
                    it.artistName
                }.sortedBy {
                    it.trackPrice
                }
            }
            filterData.postValue(afterData)
        })
    }
}