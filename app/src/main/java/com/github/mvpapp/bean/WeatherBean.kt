package com.github.mvpapp.bean

import com.github.mvpapp.http.FJBaseBean

data class WeatherBean(
    var result: Result? = null
) : FJBaseBean()

data class Result(
    val city: String,
    val future: List<Future>,
    val realtime: Realtime
)

data class Future(
    val date: String,
    val direct: String,
    val temperature: String,
    val weather: String,
    val wid: Wid
)

data class Wid(
    val day: String,
    val night: String
)

data class Realtime(
    val aqi: String,
    val direct: String,
    val humidity: String,
    val info: String,
    val power: String,
    val temperature: String,
    val wid: String
)