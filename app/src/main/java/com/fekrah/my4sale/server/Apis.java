package com.fekrah.my4sale.server;


import com.fekrah.my4sale.models.Ad;
import com.fekrah.my4sale.models.AdFavoriteResponse;
import com.fekrah.my4sale.models.AdResponse;
import com.fekrah.my4sale.models.Category;
import com.fekrah.my4sale.models.ChatsResponse;
import com.fekrah.my4sale.models.Cities;
import com.fekrah.my4sale.models.DeleteResponse;
import com.fekrah.my4sale.models.Messages;
import com.fekrah.my4sale.models.Partnerships;
import com.fekrah.my4sale.models.Room;
import com.fekrah.my4sale.models.SendMessageResponse;
import com.fekrah.my4sale.models.SlidShow;
import com.fekrah.my4sale.models.SlideImagesResponse;
import com.fekrah.my4sale.models.SubCategory;
import com.fekrah.my4sale.models.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Apis {

    // get all cities
    @GET("cities")
    Call<Cities> getCities();

    // register user client
    @Multipart
    @POST("user/register")
    Call<User> registerUser(
            @Part("username") RequestBody username,
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("mobile") RequestBody mobile,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("city_id") int city_id,
            @Part("district") RequestBody district,
            @Part MultipartBody.Part image
    );

    @Headers({"Authorization: 03974b2a88f64c84fd1671b4691483a2599fde311542202155"})
    @Multipart
    @POST("advertisement/add/type=0")
    Call<AdResponse> addAd(
            @Part("user_id") RequestBody user_id,
            @Part("category_id") RequestBody category_id,
            @Part("sub_category_id") RequestBody sub_category_id,
            @Part("city_id") RequestBody city_id,
            @Part("district") RequestBody district,
            @Part("title") RequestBody title,
            @Part("description") RequestBody description,
            @Part("price") RequestBody price,
            @Part("mobile") RequestBody mobile,
            @Part("whatsapp") RequestBody whatsapp,
            @Part("partnership_id") RequestBody partnership_id,
            @Part MultipartBody.Part image
    );

    @Headers({"Authorization: 03974b2a88f64c84fd1671b4691483a2599fde311542202155"})
    @Multipart
    @POST("advertisement/update/ads_id={id}")
    Call<DeleteResponse> upDateAd(
            @Path("id")String id,
            @Part("user_id") RequestBody user_id,
            @Part("category_id") RequestBody category_id,
            @Part("sub_category_id") RequestBody sub_category_id,
            @Part("city_id") RequestBody city_id,
            @Part("district") RequestBody district,
            @Part("title") RequestBody title,
            @Part("description") RequestBody description,
            @Part("price") RequestBody price,
            @Part("mobile") RequestBody mobile,
            @Part("whatsapp") RequestBody whatsapp,
            @Part("partnership_id") RequestBody partnership_id,
            @Part MultipartBody.Part image
    );

    // log in user client
    @FormUrlEncoded
    @POST("user/login")
    Call<User> logInClient(@Field("email") String email, @Field("password") String password);

    @GET("get/advertisement/slide_show")
    Call<SlidShow> slidShowCall();

    @GET("partnerships")
    Call<Partnerships> partnershipsCall();

    @GET("categories")
    Call<Category> categoryCall();

    @GET("subcategories/category_id={id}")
    Call<SubCategory> subCategoryCall(@Path("id") int id);

    @GET("get/advertisement/for/sub_category={id}&&page={p}")
    Call<Ad> adForSubCategoryCall(@Path("id") int id,@Path("p") int page);


    @GET("search/city_id={id1}&category_id={id2}&sub_category_id={id3}")
    Call<Ad> searchCall(@Path("id1") String c_id,@Path("id2") String ca_id,@Path("id3") String s_ca_id);

    @Headers({"Authorization: 03974b2a88f64c84fd1671b4691483a2599fde311542202155"})
    @GET("users/get/chat_list/user_id={id}")
    Call<ChatsResponse> getChatsCall(@Path("id") String id);


    @Headers({"Authorization: 03974b2a88f64c84fd1671b4691483a2599fde311542202155"})
    @GET("users/get/chat/sender_id={id}&receiver_id={id2}")
    Call<Messages> getMessagesCall(@Path("id") String sender_id,@Path("id2") String receiver_id);

    @Headers({"Authorization: 03974b2a88f64c84fd1671b4691483a2599fde311542202155"})
    @FormUrlEncoded
    @POST("add_message")
    Call<SendMessageResponse> sendMessage(@Field("sender_id") String sender_id, @Field("receiver_id") String receiver_id, @Field("message") String message);

    @Headers({"Authorization: 03974b2a88f64c84fd1671b4691483a2599fde311542202155"})
    @FormUrlEncoded
    @POST("add_to_favorite")
    Call<AdFavoriteResponse> setFavorite(@Field("user_id") String user_id, @Field("advertisement_id") String advertisement_id);

    @Headers({"Authorization: 03974b2a88f64c84fd1671b4691483a2599fde311542202155"})
    @FormUrlEncoded
    @POST("report")
    Call<AdFavoriteResponse> setReport(@Field("user_id") String user_id, @Field("advertisement_id") String advertisement_id, @Field("why") String why);

    @GET("get/advertisement/favorites/user_id={id}&&page=1")
    Call<Ad> favoriteCall(@Path("id") String id);

    @GET("get/advertisement/active/for/user/user_id={id}&&page=1")
    Call<Ad> myAds(@Path("id") String  id);

    @GET("get/last/advertisements")
    Call<Ad> lastAds(
            //   @Path("id") int id,@Path("p") int page
    );

    @DELETE("delete/advertisement/for/user/user_id={u_id}/ads_id={a_id}")
    Call<DeleteResponse> deleteMyAd(@Path("u_id") String  u_id, @Path("a_id") String  a_id);



}


//5cfe2040db3740178874044be7f6385d