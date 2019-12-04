package com.github.mvpapp.mvp.fragment

import com.github.mvpapp.R
import com.github.mvpapp.base.BaseFragment
import com.github.mvpapp.mvp.presenter.MainPresenter
import com.github.mvpapp.mvp.view.IMainView


class MainFragment : BaseFragment<IMainView,MainPresenter>(),IMainView {
    override fun createPresenter(): MainPresenter = MainPresenter()

    override fun layoutResID(): Int = R.layout.activity_main

    override fun onViewCreated() {

    }
}