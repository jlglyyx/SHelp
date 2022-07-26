package com.yang.module_main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yang.module_main.R
import com.yang.module_main.data.TaskData

/**
 * @ClassName: TaskAdapter
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/26 13:59
 */
class TaskAdapter(data: MutableList<TaskData>?) : BaseQuickAdapter<TaskData, BaseViewHolder>(data) {

    init {
        mLayoutResId = R.layout.item_task
    }

    override fun convert(helper: BaseViewHolder, item: TaskData) {

    }


}