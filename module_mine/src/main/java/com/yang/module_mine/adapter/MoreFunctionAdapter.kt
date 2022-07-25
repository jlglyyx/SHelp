package com.yang.module_mine.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yang.module_mine.R
import com.yang.module_mine.data.MoreFunctionData

/**
 * @ClassName: MoreFunctionAdapter
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/21 16:46
 */
class MoreFunctionAdapter(data: MutableList<MoreFunctionData>?) : BaseQuickAdapter<MoreFunctionData, BaseViewHolder>(data) {

    init {
        mLayoutResId = R.layout.item_mine_more_function
    }

    override fun convert(helper: BaseViewHolder, item: MoreFunctionData) {
        helper.setImageResource(R.id.iv_icon,R.drawable.iv_home)
            .setText(R.id.tv_name,item.name)
    }
}