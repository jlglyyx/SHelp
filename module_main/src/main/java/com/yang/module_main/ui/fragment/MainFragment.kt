package com.yang.module_main.ui.fragment

import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.yang.apt_annotation.annotain.InjectViewModel
import com.yang.lib_common.base.ui.fragment.BaseLazyFragment
import com.yang.lib_common.constant.AppConstant
import com.yang.lib_common.proxy.InjectViewModelProxy
import com.yang.lib_common.util.getDefaultMMKV
import com.yang.module_main.databinding.FraMainBinding
import com.yang.module_main.viewmodel.MainViewModel
import javax.inject.Inject

/**
 * @ClassName: MainFragment
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/21 15:38
 */
@Route(path = AppConstant.RoutePath.MAIN_FRAGMENT)
class MainFragment : BaseLazyFragment<FraMainBinding>() {

    @InjectViewModel
    lateinit var mainViewModel: MainViewModel


    override fun initViewBinding(): FraMainBinding {
        return bind(FraMainBinding::inflate)
    }

    override fun initView() {

    }

    override fun initData() {

        mainViewModel.getUserInfo("")
    }

    override fun initViewModel() {
        InjectViewModelProxy.inject(this)

    }
}