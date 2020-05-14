package com.example.net;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface ApiService {

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postData(@Url String url, @FieldMap Map<String, String> map);

    @POST
    Observable<ResponseBody> postDataWithout(@Url String url);


    @GET
    Observable<ResponseBody> getData(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<ResponseBody> getDataWithout(@Url String url);

    @POST
    Observable<ResponseBody> postDataWithBody(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Observable<ResponseBody> putData(@Url String url, @FieldMap Map<String, String> map, @Header("Authorization") String token);

    @PUT
    Observable<ResponseBody> putDataBody(@Url String url, @Body RequestBody body, @Header("Authorization") String token);

    @DELETE
    Observable<ResponseBody> deleteDataWithout(@Url String url, @Header("Authorization") String token);

    @HTTP(method = "DELETE", hasBody = true)
    Observable<ResponseBody> postDelete(@Url String url, @Body List<String> list, @Header("Authorization") String token);

    @HTTP(method = "DELETE", hasBody = true)
    Observable<ResponseBody> postDelete1(@Url String url, @Body List<String> list, @QueryMap Map<String, String> map, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postHead(@Url String url, @FieldMap Map<String, String> map, @Header("Authorization") String token);

    @POST
    Observable<ResponseBody> postHeadWithout(@Url String url, @Header("Authorization") String token);


    @GET
    Observable<ResponseBody> getHead(@Url String url, @QueryMap Map<String, String> map, @Header("Authorization") String token);

    @GET
    Observable<ResponseBody> getHeadWithout(@Url String url, @Header("Authorization") String token);

    @POST
    Observable<ResponseBody> postHeadWithBody(@Url String url, @Body RequestBody body, @Header("Authorization") String token);

    @POST
    Observable<ResponseBody> postHeadWithList(@Url String url, @Body List list, @Header("Authorization") String token);

    @Headers("Content-type:application/json;charset=UTF-8")
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postHeadUTF(@Url String url, @FieldMap Map<String, String> map, @Header("Authorization") String token);

    @Multipart
    @POST
    Observable<ResponseBody> postFile(@Url String url, @Part MultipartBody.Part file);
}
