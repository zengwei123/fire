package com.cnitpm.z_homeitem.Net;

import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_homeitem.Model.NewsModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NewsRequestService {

    /**
     *
     */
    @FormUrlEncoded
    @POST("appcode/GetClassList.ashx")
    Observable<AllDataState<NewsModel>> GetClassList(@Field("Username") String Username,
                                                     @Field("password") String password,
                                                     @Field("Uid") String Uid,
                                                     @Field("Type") String Type,
                                                     @Field("Type_two") String Type_two,
                                                     @Field("Page") String Page,
                                                     @Field("Code") String Code);

}
