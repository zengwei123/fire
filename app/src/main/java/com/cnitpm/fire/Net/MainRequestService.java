package com.cnitpm.fire.Net;

import com.cnitpm.fire.Model.MessageContentModel;
import com.cnitpm.fire.Model.MessageModel;
import com.cnitpm.fire.Model.SearchModel;
import com.cnitpm.fire.Model.VersionModel;
import com.cnitpm.z_common.Model.AllDataState;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface MainRequestService {
    /**
     * 会员消息列表
     */
    @FormUrlEncoded
    @POST("appcode/user/Mymsg.ashx")
    Observable<AllDataState<MessageModel>> Mymsg(@Field("Username") String Username,
                                                 @Field("password") String password,
                                                 @Field("uid") String uid,
                                                 @Field("Vipstr") String Vipstr,
                                                 @Field("page") int page,
                                                 @Field("Code") String Code, @Field("Sign") String Sign);

    /**
     * 会员消息详细内容
     */
    @FormUrlEncoded
    @POST("appcode/user/MymsgInfo.ashx")
    Observable<AllDataState<MessageContentModel>> MymsgInfo(@Field("Username") String Username,
                                                            @Field("password") String password,
                                                            @Field("uid") String uid,
                                                            @Field("Id") String Id,
                                                            @Field("page") int page,
                                                            @Field("Code") String Code, @Field("Sign") String Sign);

    /**
     * 搜索
     */
    @FormUrlEncoded
    @POST("appcode/search.ashx")
    Observable<AllDataState<List<SearchModel>>> search(@Field("keyword") String keyword, @Field("Code") String Code);

    /**
     * 用户信息及版本获取
     */
    @FormUrlEncoded
    @POST("appcode/AppVersion.ashx")
    Observable<AllDataState<VersionModel>> AppVersion(@Field("Vipstr") String Vipstr, @Field("uid") String uid, @Field("sf") String sf, @Field("Code") String Code);



    @Streaming
    @GET
    Observable<ResponseBody> downLoadFile(@Url String url);
}
