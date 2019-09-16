package com.cnitpm.z_me.Net;

import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_me.Model.ErrorTopicModel;
import com.cnitpm.z_me.Model.Record;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface MeRequestService {
    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("appcode/user/MyPassWord.ashx")
    Observable<AllDataState> MyPassWord(@Field("Username")String Username,
                                        @Field("password")String password,
                                        @Field("uid")String uid,
                                        @Field("OldPassword")String OldPassword,
                                        @Field("NewPassword")String NewPassword,
                                        @Field("Code")String Code, @Field("Sign")String Sign);

    /**
     * 答题记录
     */
    @FormUrlEncoded
    @POST("appcode/user/MyAnswerRecord.ashx")
    Observable<AllDataState<Record>> MyAnswerRecord(@Field("Username")String Username,
                                                    @Field("password")String password,
                                                    @Field("uid")String uid,
                                                    @Field("page")String page,
                                                    @Field("Code")String Code, @Field("Sign")String Sign);

    /**
     * c错题记录
     */
    @FormUrlEncoded
    @POST("appcode/user/MyExamError.ashx")
    Observable<AllDataState<ErrorTopicModel>> MyExamError(@Field("Username")String Username,
                                                            @Field("password")String password,
                                                            @Field("uid")String uid,
                                                            @Field("page")String page,
                                                            @Field("Code")String Code, @Field("Sign")String Sign);

    /**
     * c错题记录
     */
    @FormUrlEncoded
    @POST("appcode/GetShitiExplain.ashx")
    Observable<AllDataState<String>> GetShitiExplain(@Field("Username")String Username,
                                                          @Field("password")String password,
                                                          @Field("uid")String uid,
                                                          @Field("tid")String tid,
                                                          @Field("Code")String Code, @Field("Sign")String Sign);

    /**
     * 删除错题记录
     */
    @FormUrlEncoded
    @POST("appcode/user/DelExamErrors.ashx")
    Observable<AllDataState> DelExamErrors(@Field("Username")String Username,
                                                     @Field("password")String password,
                                                     @Field("uid")String uid,
                                                     @Field("id")String id,
                                                     @Field("Code")String Code, @Field("Sign")String Sign);
}
