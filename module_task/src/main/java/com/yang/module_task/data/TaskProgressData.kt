package com.yang.module_task.data

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @ClassName: TaskData
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/26 14:45
 */
class TaskProgressData(var mItemType:Int,var mLevel:Int): AbstractExpandableItem<TaskProgressData>(),MultiItemEntity {


    override fun getLevel(): Int {
        return mLevel
    }

    override fun getItemType(): Int {
        return mItemType
    }


}
