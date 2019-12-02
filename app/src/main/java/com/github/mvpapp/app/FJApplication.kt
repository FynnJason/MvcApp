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
package com.github.mvpapp.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.github.mvpapp.http.FJHttpUtils
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import me.jessyan.autosize.AutoSizeConfig


/**
 * 初始化APP
 */
class FJApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FJHttpUtils.init(this)
        AutoSizeConfig.getInstance()
            .getUnitsManager()
            .setSupportDP(true)
            .setSupportSP(true)
        Utils.init(this)
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            MaterialHeader(context)
        }
    }
}