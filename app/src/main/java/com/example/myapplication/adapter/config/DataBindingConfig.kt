package com.example.architecture.config


class DataBindingConfig @JvmOverloads constructor(val layout: Int, val vmVariableId: Int) {

     val bindingParams = mutableMapOf<Int,Any>()

    fun addBindingParam(variableId: Int, any: Any): DataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams[variableId] = any
        }
        return this
    }


}