package com.yang.module_task.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yang.lib_common.widget.AccountImageListView
import com.yang.module_task.data.TaskProgressData
import com.yang.module_task.R

/**
 * @ClassName: TaskAdapter
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/26 13:59
 */
class TaskAdapter(data: MutableList<TaskProgressData>?) : BaseMultiItemQuickAdapter<TaskProgressData, BaseViewHolder>(data) {

    init {
        addItemType(0,R.layout.item_task)
        addItemType(1,R.layout.item_task_parent)
    }

    override fun convert(helper: BaseViewHolder, item: TaskProgressData) {

        when(item.itemType){
            1 ->{
                helper.itemView.setOnClickListener {
                    val pos = helper.absoluteAdapterPosition
                    if (item.isExpanded){
                        collapse(pos)
                    }else{
                        expand(pos)
                    }
                }
            }
        }
    }


}