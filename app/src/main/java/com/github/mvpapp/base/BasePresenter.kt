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
package com.github.mvpapp.base

import java.lang.ref.WeakReference

abstract class BasePresenter<T> {

    private var mWeakReference: WeakReference<T>? = null

    fun attachView(t: T) {
        mWeakReference = WeakReference(t)
    }

    fun getView() = mWeakReference?.get()

    fun detachView() {
        mWeakReference?.clear()
        mWeakReference = null
    }
}