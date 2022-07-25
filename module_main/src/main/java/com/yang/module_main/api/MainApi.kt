package com.yang.module_main.api

import com.yang.lib_common.constant.AppConstant
import com.yang.lib_common.data.LoginData
import com.yang.lib_common.remote.di.response.MResult
import com.yang.lib_common.room.entity.UserInfoData
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @ClassName: MainApi
 * @Description:
 * @Author: yxy
 * @Date: 2022/7/21 14:16
 */
interface MainApi {

    @GET("/")
    suspend fun getA():String

    @POST("api/user/login")
    suspend fun login(@QueryMap params:MutableMap<String,Any>):MResult<LoginData>

    @POST("api/user/query/userInfo")
    suspend fun getUserInfo(@Query(AppConstant.Constant.ID) id:String):MResult<UserInfoData>
}