package com.yang.module_main.viewmodel

import android.app.Application
import android.text.TextUtils
import com.yang.lib_common.base.viewmodel.BaseViewModel
import com.yang.lib_common.bus.event.LiveDataBus
import com.yang.lib_common.constant.AppConstant
import com.yang.lib_common.data.LoginData
import com.yang.lib_common.util.getDefaultMMKV
import com.yang.lib_common.util.showShort
import com.yang.lib_common.util.toJson
import com.yang.module_main.repository.MainRepository
import javax.inject.Inject

/**
 * @ClassName: MainViewModel
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/21 14:13
 */
class MainViewModel @Inject constructor(
    application: Application,
    private val mainRepository: MainRepository
) : BaseViewModel(application) {

    var loginType = 0

    var phoneText = ""

    var passwordText = ""

    var verificationText = ""

    var checkStatus = true

    fun getA() {
        launch({
            mainRepository.getA()
        }, {
            //showShort(it)
        })
    }


    fun login() {
        if (TextUtils.isEmpty(phoneText)) {
            showShort("请输入手机号")
            return
        }
        if (loginType == 1) {
            if (TextUtils.isEmpty(passwordText)) {
                showShort("请输入密码")
                return
            }
        } else {
            if (TextUtils.isEmpty(verificationText)) {
                showShort("请输入验证码")
                return
            }
        }
        if (!checkStatus) {
            showShort("请勾选协议")
            return
        }

        launch({
            val params = mutableMapOf<String,Any>()
            params[AppConstant.Constant.PHONE] = phoneText
            params[AppConstant.Constant.PASSWORD] = passwordText
            params[AppConstant.Constant.VERIFICATION] = verificationText
           mainRepository.login(params)
        }, {
            finishActivity()
        },{
            getDefaultMMKV().putString(AppConstant.Constant.LOGIN_INFO, LoginData("token","id").toJson())
            getDefaultMMKV().putInt(AppConstant.Constant.LOGIN_STATUS,AppConstant.Constant.LOGIN_SUCCESS)
            LiveDataBus.instance.with(AppConstant.Constant.LOGIN_STATUS).postValue(AppConstant.Constant.LOGIN_SUCCESS)
            finishActivity()
        },messages = arrayOf("登录中...","登录成功!"))
    }


    fun getUserInfo(id:String){
        launch({
            mainRepository.getUserInfo(id)
        },{

        })
    }

}