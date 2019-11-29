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
package com.github.mvpapp.mvp.activity

import android.os.Bundle
import com.github.mvpapp.R
import com.github.mvpapp.base.BaseActivity
import com.github.mvpapp.bean.WeatherBean
import com.github.mvpapp.mvp.presenter.MainPresenter
import com.github.mvpapp.mvp.view.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<IMainView, MainPresenter>(), IMainView {

    override fun createPresenter(): MainPresenter = MainPresenter()

    override fun layoutResID(): Int = R.layout.activity_main

    override fun createActivity(savedInstanceState: Bundle?) {
        initListener()
    }

    private fun initListener() {
        btn_load_weather.setOnClickListener {
            val city = if (et_city.text.toString().isEmpty()) "成都" else et_city.text.toString()
            mPresenter?.loadWeather(city)
        }
    }

    override fun onSuccessWeather(t: WeatherBean, json: String) {
        tv_weather.text = json
    }

    override fun onErrorWeather(errorCode: Int, errorMsg: String) {
        tv_weather.text = errorMsg
    }

}
