package com.example.architecture.config


class DataBindingAdapterConfig @JvmOverloads constructor() {

     val bindingParams = mutableMapOf<Int,Any>()

    fun addBindingParam(variableId: Int, any: Any): DataBindingAdapterConfig {
        if (bindingParams[variableId] == null) {
            bindingParams[variableId] = any
        }
        return this
    }


}