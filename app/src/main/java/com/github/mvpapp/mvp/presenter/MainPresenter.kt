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
package com.github.mvpapp.mvp.presenter

import com.github.mvpapp.base.BasePresenter
import com.github.mvpapp.bean.WeatherBean
import com.github.mvpapp.mvp.model.IMainModel
import com.github.mvpapp.mvp.model.MainModel
import com.github.mvpapp.mvp.view.IMainView

/**
 * MainActivity的Presenter
 */
class MainPresenter : BasePresenter<IMainView>() {

    private val mainModel = MainModel()

    fun loadWeather(city: String) {
        getView()?.showLoading()
        mainModel.loadWeather(city, object : IMainModel.OnWeatherListener {
            override fun onSuccessWeather(t: WeatherBean, json: String) {
                getView()?.hideLoading()
                getView()?.onSuccessWeather(t, json)
            }

            override fun onErrorWeather(errorCode: Int, errorMsg: String) {
                getView()?.hideLoading()
                getView()?.onErrorWeather(errorCode, errorMsg)
            }
        })
    }
}