package com.cnitpm.z_subjects.Net;

import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_subjects.Model.SubjectsModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SubjectsRequestService {

    /**
     *
     */
    @FormUrlEncoded
    @POST("appcode/GetClassList.ashx")
    Observable<AllDataState<SubjectsModel>> GetClassList(@Field("Username") String Username,
                                                         @Field("password") String password,
                                                         @Field("Uid") String Uid,
                                                         @Field("Type") String Type,
                                                         @Field("page") int page,
                                                         @Field("Code") String Code);

    /**
     * 考试开始时间
     */
    @FormUrlEncoded
    @POST("appcode/HomePage.ashx")
    Observable<AllDataState<String>> HomePage2(@Field("Type") String Type, @Field("Code") String Code);

    /**
     *章节练习
     */
    @FormUrlEncoded
    @POST("appcode/GetClassList.ashx")
    Observable<AllDataState<SubjectsModel>> GetClassList1(@Field("Username") String Username,
                                                          @Field("password") String password,
                                                          @Field("Uid") String Uid,
                                                          @Field("Type") String Type,
                                                         @Field("Type_two") String Type_two,
                                                         @Field("page") int page,
                                                         @Field("Code") String Code);
}
