package com.example.productsapplication.utils

import android.content.Context

fun Context.saveLastScreen(screen:String, productId:Int){
    val prefs = getSharedPreferences("app_state",Context.MODE_PRIVATE)
    prefs.edit().putString("last_screen",screen).putInt("product_id",productId).apply()
}
fun Context.getLastScreen():Pair<String,Int>{
    val prefs = getSharedPreferences("app_state",Context.MODE_PRIVATE)
    return Pair(prefs.getString("last_screen","list")!!,prefs.getInt("product_id",-1))
}