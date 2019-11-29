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
package com.github.mvpapp.http

import android.util.Log
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.DeleteRequest
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest
import com.lzy.okgo.request.PutRequest

/**
 * 网络请求框架构建体，统一处理结果后回调
 */

open class FJHttpHelper<T : FJBaseBean>(t: T) {

    var apiUrl: String? = null
    private var callback: FJHttpCallback<T>? = null
    private var getRequest: GetRequest<String>? = null
    private var postRequest: PostRequest<String>? = null
    private var putRequest: PutRequest<String>? = null
    private var deleteRequest: DeleteRequest<String>? = null

    // get
    fun get(apiUrl: String): GetRequest<String> {
        getRequest = OkGo.get<String>(apiUrl).tag(apiUrl)
        return getRequest!!
    }

    // post
    fun post(apiUrl: String): PostRequest<String> {
        postRequest = OkGo.post<String>(apiUrl).tag(apiUrl)
        return postRequest!!
    }

    // put
    fun put(apiUrl: String): PutRequest<String> {
        putRequest = OkGo.put<String>(apiUrl).tag(apiUrl)
        return putRequest!!
    }

    // delete
    fun delete(apiUrl: String): DeleteRequest<String> {
        deleteRequest = OkGo.delete<String>(apiUrl).tag(apiUrl)
        return deleteRequest!!
    }

    // 执行
    fun execute(callback: FJHttpCallback<T>) {
        this.callback = callback
        getRequest?.execute(stringCallback)
        postRequest?.execute(stringCallback)
        putRequest?.execute(stringCallback)
        deleteRequest?.execute(stringCallback)
    }

    // 统一处理网络请求结果
    val stringCallback: StringCallback? = object : StringCallback() {
        @Suppress("UNCHECKED_CAST")
        override fun onSuccess(response: Response<String>) {
            try {
                val baseBean: Any = Gson().fromJson(response.body(), t.javaClass)
                if ((baseBean as T).code == FJApi.SUCCESS_CODE) {
                    callback!!.onSuccess(baseBean, response.body())
                } else {
                    callback!!.onError(baseBean.code, baseBean.msg)
                }
            } catch (e: Exception) {
                Log.e("网络请求异常", "--->${e.message}")
                callback!!.onError(FJApi.EXCEPTION_CODE, e.message ?: "数据异常")
            }
        }

        override fun onError(response: Response<String>) {
            callback!!.onError(FJApi.NOT_NETWORK_CODE, "网络已断开")
        }
    }
}