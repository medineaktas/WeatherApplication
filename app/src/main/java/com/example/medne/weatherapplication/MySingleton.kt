package com.example.medne.weatherapplication

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by medne on 23.02.2018.
 */
class MySingleton private constructor(context: Context){

    private  var mRequestQuene: RequestQueue?=null


    val requestQuene: RequestQueue?
        get() {

            if (mRequestQuene==null){
                mRequestQuene= Volley.newRequestQueue(mCtx?.applicationContext)
            }
            return mRequestQuene
        }
    init {
        mCtx=context
        mRequestQuene=requestQuene

    }
    fun <T>addToRequestQuene(req: Request<T>){
        requestQuene?.add(req)
    }
    companion object {
        private var mInstance: MySingleton? = null
        private var mCtx: Context? = null
        @Synchronized
        fun getInstance(context: Context?): MySingleton {
            if (mInstance == null) {
                mInstance = MySingleton(context!!)
            }
            return mInstance as MySingleton

        }
    }
}