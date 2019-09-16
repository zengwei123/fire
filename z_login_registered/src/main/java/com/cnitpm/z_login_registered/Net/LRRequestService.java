package com.cnitpm.z_login_registered.Net;

import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LRRequestService {
    /**
     * 短信验证接口
     */
    @FormUrlEncoded
    @POST("appcode/sendsms.ashx")
    Observable<AllDataState> sendsms(@Field("stype")int stype, @Field("mobile")String mobile, @Field("Code")String Code, @Field("Sign")String Sign);

    /**
     * 登录接口
     */
    @FormUrlEncoded
    @POST("appcode/Login_MessCode.ashx")
    Observable<AllDataState<UserMessage>> Login_MessCode(@Field("tel")String tel, @Field("yzcode")String yzcode, @Field("Code")String Code, @Field("Sign")String Sign);

    /**
     * 登录接口
     */
    @FormUrlEncoded
    @POST("appcode/Login.ashx")
    Observable<AllDataState<UserMessage>> Login(@Field("Username")String Username, @Field("password")String password, @Field("Code")String Code, @Field("Sign")String Sign);


    @GET
    Call<ResponseBody> downloadPicFromNet(@Url String fileUrl);   //图片验证码

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("appcode/RegUser.ashx")
    Observable<AllDataState<UserMessage>> RegUser(@Field("userphone")String userphone, @Field("userpass")String userpass,
                                     @Field("userqq")String userqq,@Field("smscode")String smscode,
                                     @Field("RegType")String RegType,
                                     @Field("Code")String Code, @Field("Sign")String Sign);

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("appcode/PasswordForgot.ashx")
    Observable<AllDataState> PasswordForgot(@Field("userphone")String userphone, @Field("userpass")String userpass,
                                   @Field("smscode")String smscode,
                                     @Field("Code")String Code, @Field("Sign")String Sign);
}
