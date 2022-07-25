package com.yang.module_mine.di.component

import com.yang.lib_common.remote.di.component.RemoteComponent
import com.yang.lib_common.scope.ActivityScope
import com.yang.module_mine.di.factory.MineViewModelFactory
import com.yang.module_mine.di.module.MineModule
import com.yang.module_mine.ui.fragment.MineFragment
import dagger.Component

/**
 * @ClassName: Compent
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/21 14:24
 */
@ActivityScope
@Component(modules = [MineModule::class],dependencies = [RemoteComponent::class])
interface MineComponent {

    fun provideMineViewModelFactory(): MineViewModelFactory


    fun inject(mainFragment: MineFragment)
}