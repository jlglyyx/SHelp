package com.yang.module_mine.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.yang.lib_common.base.ui.activity.BaseActivity
import com.yang.lib_common.constant.AppConstant
import com.yang.module_mine.databinding.ActMyBalanceBinding
import com.yang.module_mine.databinding.ActMyInfoBinding
import com.yang.module_mine.databinding.ActMyRightsBinding

/**
 * @ClassName: MyInfoActivity
 * @Description:
 * @Author: yxy
 * @Date: 2022/8/4 17:09
 */
@Route(path = AppConstant.RoutePath.MINE_MY_INFO_ACTIVITY)
class MyInfoActivity : BaseActivity<ActMyInfoBinding>() {
    override fun initViewBinding(): ActMyInfoBinding {
        return bind(ActMyInfoBinding::inflate)
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initViewModel() {

    }
}