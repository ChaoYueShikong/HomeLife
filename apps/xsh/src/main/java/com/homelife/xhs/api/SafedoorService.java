package com.homelife.xhs.api;

import com.homelife.net.Feed;
import com.homelife.xhs.bean.ImgInfo;
import com.homelife.xhs.bean.LoginBean;
import com.homelife.xhs.bean.LoginResponse;
import com.homelife.xhs.bean.Satin;
import com.homelife.xhs.bean.SysUserResponse;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Description 所有API接口
 * @Author Xue
 */
public interface SafedoorService {


    @FormUrlEncoded
    @POST("cppt-app/terninal/operation.do")
    Single<Feed> getInfo(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("cppt-app/terninal/operation.do")
    Single<Feed<ImgInfo>> getBgImg(@FieldMap Map<String, String> map);

    @GET("satinApi")
    @Headers("Content-Type:application/json")
    Single<Feed<List<Satin>>> getSatin(@QueryMap Map<String, String> params);

    @Streaming
    @GET
    Single<File> downloadFile(@Url String fileUrl);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile2(@Url String fileUrl);


    @POST("userapi/login")
    Single<LoginResponse> onLogin(@Body RequestBody body);


    Single<SysUserResponse> obtainSysnInfo(@Header("account-id") String account_id,
                                           @Header("auth-token") String auth_token,
                                           @Header("ver") String ver);
}
