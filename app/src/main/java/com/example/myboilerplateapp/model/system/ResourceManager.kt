package com.example.myboilerplateapp.model.system

import android.content.Context
import javax.inject.Inject

class ResourceManager @Inject constructor(private val context: Context) {
    fun getString(res: Int, vararg formatArgs: Any): String = context.getString(res, *formatArgs)

    fun getString(res: Int): String = context.getString(res)


}