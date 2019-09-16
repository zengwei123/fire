package com.cnitpm.z_daypractice.Net;

import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_daypractice.Model.DayModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DayRequestService {

    /**
     * 每日一练数据加载
     */
    @FormUrlEncoded
    @POST("appcode/GetClassList.ashx")
    Observable<AllDataState<DayModel>> GetClassList(@Field("Username") String Username,
                                                    @Field("password") String password,
                                                    @Field("Uid") String Uid,
                                                    @Field("Type") String Type,
                                                    @Field("Type_two") String Type_two,
                                                    @Field("kemuX") String kemuX,
                                                    @Field("Month") String Month,
                                                    @Field("Page") String Page,
                                                    @Field("Code") String Code);
}
