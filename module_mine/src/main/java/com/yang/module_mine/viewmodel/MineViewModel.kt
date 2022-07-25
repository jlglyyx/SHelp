package com.yang.module_mine.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.yang.lib_common.base.viewmodel.BaseViewModel
import com.yang.lib_common.room.entity.UserInfoData
import com.yang.lib_common.util.showShort
import com.yang.module_mine.repository.MineRepository
import javax.inject.Inject

/**
 * @ClassName: MineViewModel
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/21 14:13
 */
class MineViewModel @Inject constructor(application: Application, private val mainRepository: MineRepository) :BaseViewModel(application) {

    val userInfoData = MutableLiveData<UserInfoData>()

    fun getA(){
        launch({
            mainRepository.getA()
        },{
            showShort(it)
        })
    }


    fun getUserInfo(id:String){
        launch({
            mainRepository.getUserInfo(id)
        },{
            val userInfoData1 = UserInfoData("0","sahk",null,0,10,null,null,"0","",
                "","","","","","","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F99689cbe5898812b3b1340545c08847a430d047f.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1661329266&t=3bd482aedc9184e4a91f252914b2291f",0,0,true,
                0,"","","","","","")
            userInfoData.postValue(userInfoData1)
        },{
            val userInfoData1 = UserInfoData("0","sahk",null,0,10,null,null,"0","",
                "","","","","","","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F99689cbe5898812b3b1340545c08847a430d047f.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1661329266&t=3bd482aedc9184e4a91f252914b2291f",0,0,true,
                0,"","","","","","")
            userInfoData.postValue(userInfoData1)
        })

    }
}