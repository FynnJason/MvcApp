/*
 * Copyright (C) 2019 FynnJason.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.mvpapp.mvp.model

import com.github.mvpapp.bean.WeatherBean
import com.github.mvpapp.http.FJHttp
import com.github.mvpapp.http.FJHttpCallback

/**
 * MainActivity的Model
 */
class MainModel : IMainModel {

    override fun loadWeather(city: String, listener: IMainModel.OnWeatherListener) {
        FJHttp(WeatherBean())
            .getWeatherByCity(city)
            .execute(object : FJHttpCallback<WeatherBean> {
                override fun onSuccess(t: WeatherBean, json: String) {
                    listener.onSuccessWeather(t, json)
                }

                override fun onError(errorCode: Int, errorMsg: String) {
                    listener.onErrorWeather(errorCode, errorMsg)
                }
            })
    }
}