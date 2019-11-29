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
package com.github.mvcapp.http

import android.app.Application
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level


/**
 * 网络请求框架的初始化工具类
 */
object FJHttpUtils {

    private const val logTag = "FJHttp" // 日志输出的tag
    private const val readTime: Long = 60 // 全局读取时间
    private const val writeTime: Long = 60 // 全局写入时间
    private const val connectTime: Long = 60 // 全局连接时间

    // 构建初始化
    fun init(application: Application) {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(readTime, TimeUnit.SECONDS)
        builder.writeTimeout(writeTime, TimeUnit.SECONDS)
        builder.connectTimeout(connectTime, TimeUnit.SECONDS)
        // 构建日志拦截器
        val httpLoggingInterceptor = HttpLoggingInterceptor(logTag)
        httpLoggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        httpLoggingInterceptor.setColorLevel(Level.INFO)
        // 添加日志拦截器
        builder.addInterceptor(httpLoggingInterceptor)
        // 构建OkGo
        OkGo.getInstance()
                .init(application)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .retryCount = 3
    }
}