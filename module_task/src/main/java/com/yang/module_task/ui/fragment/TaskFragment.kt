package com.yang.module_task.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.yang.apt_annotation.annotain.InjectViewModel
import com.yang.lib_common.base.ui.fragment.BaseLazyFragment
import com.yang.lib_common.bus.event.UIChangeLiveData
import com.yang.lib_common.constant.AppConstant
import com.yang.lib_common.proxy.InjectViewModelProxy
import com.yang.lib_common.util.buildARouter
import com.yang.lib_common.widget.CommonToolBar
import com.yang.module_task.adapter.TaskAdapter
import com.yang.module_task.viewmodel.TaskViewModel
import com.yang.module_task.databinding.FraTaskBinding

/**
 * @ClassName: TaskFragment
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/21 15:38
 */
@Route(path = AppConstant.RoutePath.TASK_FRAGMENT)
class TaskFragment : BaseLazyFragment<FraTaskBinding>(), OnRefreshLoadMoreListener {

    @InjectViewModel
    lateinit var taskViewModel: TaskViewModel

    lateinit var taskAdapter: TaskAdapter

    override fun initViewBinding(): FraTaskBinding {
        return bind(FraTaskBinding::inflate)
    }

    override fun initUIChangeLiveData(): UIChangeLiveData? {
        return taskViewModel.uC
    }

    override fun initView() {
        mViewBinding.commonToolBar.imageAddCallBack = object : CommonToolBar.ImageAddCallBack{
            override fun imageAddClickListener() {
                buildARouter(AppConstant.RoutePath.ADD_TASK_ACTIVITY).navigation()
            }

        }
        initSmartRefreshLayout()
        initRecyclerView()
    }


    override fun initData() {

        taskViewModel.getTaskList()
    }

    private fun initSmartRefreshLayout() {
        mViewBinding.includeContainer.smartRefreshLayout.setOnRefreshLoadMoreListener(this)

    }

    private fun initRecyclerView() {
        taskAdapter = TaskAdapter(mutableListOf())
        taskAdapter.setOnItemClickListener { adapter, view, position ->
            if (position == 1){
                buildARouter(AppConstant.RoutePath.TASK_SELLER_PROGRESS_ACTIVITY).navigation()
            }else{
                buildARouter(AppConstant.RoutePath.TASK_BUYER_PROGRESS_ACTIVITY).navigation()
            }

        }
        mViewBinding.includeContainer.recyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        mViewBinding.includeContainer.recyclerView.adapter = taskAdapter
        registerRefreshAndRecyclerView(
            mViewBinding.includeContainer.smartRefreshLayout,
            taskAdapter
        )
    }

    override fun initViewModel() {
        InjectViewModelProxy.inject(this)
        taskViewModel.taskLiveData.observe(this, Observer {
            when {
                mViewBinding.includeContainer.smartRefreshLayout.isRefreshing -> {
                    taskViewModel.uC.refreshEvent.call()
                    if (it.isNullOrEmpty()) {
                        taskViewModel.showRecyclerViewEmptyEvent()
                    } else {
                        taskAdapter.replaceData(it)
                        taskAdapter.expandAll()
                    }
                }
                mViewBinding.includeContainer.smartRefreshLayout.isLoading -> {
                    taskViewModel.uC.loadMoreEvent.call()
                    if (it.isNullOrEmpty()) {
                        mViewBinding.includeContainer.smartRefreshLayout.setNoMoreData(true)
                    } else {
                        mViewBinding.includeContainer.smartRefreshLayout.setNoMoreData(false)
                        taskAdapter.addData(it)
                        taskAdapter.expandAll()
                    }
                }
                else -> {
                    if (it.isNullOrEmpty()) {
                        taskViewModel.showRecyclerViewEmptyEvent()
                    } else {
                        taskAdapter.replaceData(it)
                        taskAdapter.expandAll()
                    }
                }
            }
        })
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        taskViewModel.pageNum = 1
        taskViewModel.getTaskList()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        taskViewModel.pageNum++
        taskViewModel.getTaskList()
    }

}