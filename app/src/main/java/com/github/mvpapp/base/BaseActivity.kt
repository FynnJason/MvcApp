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

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mvpapp.mvp.view.IMainView

abstract class BaseActivity<V, T : BasePresenter<V>> : AppCompatActivity(), IMainView {
    var mPresenter: T? = null
    abstract fun createPresenter(): T
    abstract fun layoutResID(): Int
    abstract fun createActivity(savedInstanceState: Bundle?)
    private var mLoadingView: ProgressDialog? = null

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResID())
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
        createActivity(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    override fun showLoading() {
        if (mLoadingView == null) {
            mLoadingView = ProgressDialog(this)
            mLoadingView!!.setMessage("加载中，请等待")
            mLoadingView!!.setCanceledOnTouchOutside(false)
        }
        mLoadingView?.show()
    }

    override fun hideLoading() {
        mLoadingView?.cancel()
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        val fragmentTag = "android:support:fragments"
        outState.remove(fragmentTag)
    }
}