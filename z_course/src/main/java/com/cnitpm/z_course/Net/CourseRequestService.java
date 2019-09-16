package com.cnitpm.z_course.Net;

import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_course.Model.CourseModel;
import com.cnitpm.z_course.Model.VideoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CourseRequestService {

    /**
     *
     */
    @FormUrlEncoded
    @POST("appcode/user/shipin.ashx")
    Observable<AllDataState<List<CourseModel>>> shipin(@Field("Username") String Username,
                                                       @Field("password") String password,
                                                       @Field("uid") String uid,
                                                       @Field("Vipstr") String Vipstr,
                                                       @Field("type") String type,
                                                       @Field("Code") String Code,@Field("Sign") String Sign);


    @FormUrlEncoded
    @POST("appcode/user/mob_play.ashx")
    Observable<AllDataState<VideoModel>> mob_play(@Field("Username") String Username,
                                                  @Field("password") String password,
                                                  @Field("uid") String uid,
                                                  @Field("Vipstr") String Vipstr,
                                                  @Field("vsid") String vsid,
                                                  @Field("gq") String gq,
                                                  @Field("type") String type,
                                                  @Field("Code") String Code, @Field("Sign") String Sign);

}
