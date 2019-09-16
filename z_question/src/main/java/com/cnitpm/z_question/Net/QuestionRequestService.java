package com.cnitpm.z_question.Net;

import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_question.Model.ParsingModel;
import com.cnitpm.z_question.Model.TrueTopicModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionRequestService {

    /**
     * 考试开始时间
     */
    @FormUrlEncoded
    @POST("appcode/HomePage.ashx")
    Observable<AllDataState<String>> HomePage2(@Field("Type") String Type, @Field("Code") String Code);

    /**
     * 历年真题
     */
    @FormUrlEncoded
    @POST("appcode/GetExamList.ashx")
    Observable<AllDataState<List<TrueTopicModel>>> GetExamList(@Field("Username") String Username,
                                                               @Field("password") String password,
                                                               @Field("Uid") String Uid,@Field("Type") int Type, @Field("Type_two") int Type_two, @Field("Code") String Code);

    /**
     * 试题解析
     */
    @FormUrlEncoded
    @POST("appcode/GetClassList.ashx")
    Observable<AllDataState<ParsingModel>> GetClassList(@Field("Username") String Username,
                                                        @Field("password") String password,
                                                        @Field("Uid") String Uid,
                                                        @Field("Type") int Type,
                                                        @Field("Type_two") int Type_two,
                                                        @Field("page") int page,
                                                        @Field("Code") String Code);

}
