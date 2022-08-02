package com.yang.module_mine.ui.fragment

import android.graphics.Bitmap
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.huawei.hms.hmsscankit.ScanUtil
import com.yang.apt_annotation.annotain.InjectViewModel
import com.yang.lib_common.adapter.MBannerAdapter
import com.yang.lib_common.base.ui.fragment.BaseFragment
import com.yang.lib_common.base.ui.fragment.BaseLazyFragment
import com.yang.lib_common.bus.event.LiveDataBus
import com.yang.lib_common.constant.AppConstant
import com.yang.lib_common.data.BannerBean
import com.yang.lib_common.data.LoginData
import com.yang.lib_common.proxy.InjectViewModelProxy
import com.yang.lib_common.util.*
import com.yang.module_mine.R
import com.yang.module_mine.adapter.MoreFunctionAdapter
import com.yang.module_mine.data.MoreFunctionData
import com.yang.module_mine.databinding.FraMineBinding
import com.yang.module_mine.viewmodel.MineViewModel
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.indicator.CircleIndicator
import javax.inject.Inject

/**
 * @ClassName: MineFragment
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/21 15:38
 */
@Route(path = AppConstant.RoutePath.MINE_FRAGMENT)
class MineFragment : BaseFragment<FraMineBinding>() {

    @InjectViewModel
    lateinit var mineViewModel: MineViewModel


    lateinit var moreFunctionAdapter: MoreFunctionAdapter

    private var buildBitmap: Bitmap? = null

    override fun initViewBinding(): FraMineBinding {
        return bind(FraMineBinding::inflate)
    }

    override fun setStatusPadding(): Boolean {
        return true
    }

    override fun initView() {
        mViewBinding.tvLogin.clicks().subscribe {
            buildARouterLogin(requireContext())
        }
        initBanner()
        initRecyclerView()

        val loginStatus = getDefaultMMKV().getInt(AppConstant.Constant.LOGIN_STATUS, -1)
        val loginUserType = getDefaultMMKV().getInt(AppConstant.Constant.LOGIN_USER_TYPE, 0)
        if (loginStatus == AppConstant.Constant.LOGIN_SUCCESS){
            mViewBinding.clHead.visibility = View.GONE
            mViewBinding.lottieAnimationView.visibility = View.GONE
            mViewBinding.clHeadLogin.visibility = View.VISIBLE
            mViewBinding.tvLoginUserType.text = if (loginUserType == 0) "买家版" else "卖家版"
        }


    }

    override fun initData() {
        LiveDataBus.instance.with(AppConstant.Constant.LOGIN_STATUS).observe(this, Observer {
            if (it == AppConstant.Constant.LOGIN_SUCCESS) {
                mViewBinding.clHead.visibility = View.GONE
                mViewBinding.lottieAnimationView.visibility = View.GONE
                mViewBinding.clHeadLogin.visibility = View.VISIBLE
                val loginUserType = getDefaultMMKV().getInt(AppConstant.Constant.LOGIN_USER_TYPE, 0)
                mViewBinding.tvLoginUserType.text = if (loginUserType == 0) "买家版" else "卖家版"
            } else {
                mViewBinding.clHead.visibility = View.VISIBLE
                mViewBinding.lottieAnimationView.visibility = View.VISIBLE
                mViewBinding.clHeadLogin.visibility = View.GONE
            }
        })

    }

    override fun onResume() {
        super.onResume()
        getDefaultMMKV().getString(AppConstant.Constant.LOGIN_INFO, "")
            ?.fromJson(LoginData::class.java)?.let {
            mineViewModel.getUserInfo(it.id)
        }
    }

    private fun initRecyclerView() {
        moreFunctionAdapter = MoreFunctionAdapter(mutableListOf<MoreFunctionData>().apply {
            add(MoreFunctionData(R.drawable.iv_home,"消息通知",AppConstant.RoutePath.LOGIN_ACTIVITY))
            add(MoreFunctionData(R.drawable.iv_add,"联系客服",AppConstant.RoutePath.LOGIN_ACTIVITY))
            add(MoreFunctionData(R.drawable.iv_add,"意见反馈",AppConstant.RoutePath.LOGIN_ACTIVITY))
            add(MoreFunctionData(R.drawable.iv_add,"关于App",AppConstant.RoutePath.LOGIN_ACTIVITY))
            add(MoreFunctionData(R.drawable.iv_setting,"设置",AppConstant.RoutePath.MINE_SETTING_ACTIVITY))
        })

        moreFunctionAdapter.setOnItemClickListener { adapter, view, position ->
                val item = moreFunctionAdapter.getItem(position)
            item?.let {
                buildARouter(it.routePath).navigation()
            }
        }

        mViewBinding.recyclerView.adapter = moreFunctionAdapter
        mViewBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
    }

    private fun initBanner() {
        mViewBinding.banner.addBannerLifecycleObserver(this)
            .setAdapter(MBannerAdapter(mutableListOf<BannerBean>().apply {
                add(BannerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201606%2F23%2F20160623142756_YyXNw.thumb.400_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660986299&t=9fc850da14dfd73a1d7c1c3f068e3d57"))
                add(BannerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201607%2F29%2F20160729224352_rVhZA.thumb.400_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660986299&t=326f6993d0c1ee5c0048b33fe9f0a6dc"))
                add(BannerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201508%2F15%2F20150815231707_JWQjx.thumb.400_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660986299&t=e3951129e167261b3f4c7c739becc463"))
            }), true)
            .isAutoLoop(true)
            .indicator = CircleIndicator(requireContext())
    }

    override fun initViewModel() {
        InjectViewModelProxy.inject(this)
        mineViewModel.userInfoData.observe(this, Observer {
            getDefaultMMKV().putString(AppConstant.Constant.USER_INFO, it.toJson())
            Glide.with(this).load(it.userImage).into(mViewBinding.sivHead)
            if (null == buildBitmap){
                buildBitmap = ScanUtil.buildBitmap("sssssssssss", 500, 500)
                mViewBinding.ivErCode.setImageBitmap(buildBitmap)
            }
        })
    }
}