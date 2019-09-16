package com.cnitpm.z_home.Net;

import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_home.Model.ExamModel;
import com.cnitpm.z_home.Model.HomeHead;
import com.cnitpm.z_home.Model.NewsModel;
import com.cnitpm.z_home.Model.OptimalModel;
import com.cnitpm.z_home.Model.P_Return;
import com.cnitpm.z_home.Model.P_WX;
import com.cnitpm.z_home.Model.P_ZFB;
import com.cnitpm.z_home.Model.pay_inform;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HomeRequestService {
    /**
     * 首页轮播图 考试开始时间
     */
    @FormUrlEncoded
    @POST("appcode/HomePage.ashx")
    Observable<AllDataState<List<HomeHead>>> HomePage1(@Field("Type") String Type, @Field("Code") String Code);
    /**
     * 首页轮播图 考试开始时间
     */
    @FormUrlEncoded
    @POST("appcode/HomePage.ashx")
    Observable<AllDataState<String>> HomePage2(@Field("Type") String Type, @Field("Code") String Code);

    /**
     * 考试价格
     */
    @FormUrlEncoded
    @POST("appcode/HomePage.ashx")
    Observable<AllDataState<OptimalModel>> HomePage3(@Field("Type") String Type, @Field("Code") String Code);


    /**
     * 最近更新 章节练习  考试练习
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

    /**
     * 最近更新
     */
    @FormUrlEncoded
    @POST("appcode/GetExamList.ashx")
    Observable<AllDataState<List<ExamModel>>> GetExamList(@Field("Username") String Username,
                                                          @Field("password") String password,
                                                          @Field("Uid") String Uid,
                                                          @Field("Type") String Type,
                                                          @Field("Code") String Code);


    /**
     * 支付信息
     */
    @FormUrlEncoded
    @POST("appcode/pay/pay_inform.ashx")
    Observable<AllDataState<List<pay_inform>>> pay_inform(@Field("Code") String Code);

    /**
     * 支付结果验证
     */
    @FormUrlEncoded
    @POST("appcode/pay/return_url.ashx")
    Observable<AllDataState<P_Return>> pay_return_url(@Field("TradeNo")String TradeNo, @Field("Code") String Code);

    /**
     * 支付宝支付
     */
    @FormUrlEncoded
    @POST("appcode/pay/alipay.ashx")
    Observable<AllDataState<P_ZFB>> alipay(@Field("type") int type,
                                          @Field("yhm") String yhm,
                                          @Field("xm") String xm,
                                          @Field("sfz") String sfz,
                                          @Field("uid") String uid,
                                          @Field("Code") String Code);

    /**
     * 支付宝支付
     */
    @FormUrlEncoded
    @POST("appcode/pay/WxPay.ashx")
    Observable<AllDataState<P_WX>> WxPay(@Field("type") int type,
                                          @Field("yhm") String yhm,
                                          @Field("xm") String xm,
                                          @Field("sfz") String sfz,
                                          @Field("uid") String uid,
                                          @Field("Code") String Code);

}
