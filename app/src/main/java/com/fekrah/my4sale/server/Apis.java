package com.fekrah.my4sale.server;


import com.fekrah.my4sale.models.Boat;
import com.fekrah.my4sale.models.BuyResponse;
import com.fekrah.my4sale.models.City;
import com.fekrah.my4sale.models.Marsa;
import com.fekrah.my4sale.models.User;
import com.fekrah.my4sale.models.fishes.FishAd;
import com.fekrah.my4sale.models.fishes.FishesType;
import com.fekrah.my4sale.models.periods.Period;
import com.fekrah.my4sale.models.provider_orders.ProviderFishOrder;
import com.fekrah.my4sale.models.provider_orders.ProviderTripOrder;
import com.fekrah.my4sale.models.single_trip_ad.SingleTripAd;
import com.fekrah.my4sale.models.tripType.TripType;
import com.fekrah.my4sale.models.trips.TripAd;
import com.fekrah.my4sale.models.vehicle.Vehicle;
import com.fekrah.my4sale.models.vehicle.VehicleResponce;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Apis {

    // get all cities
    @GET("cities/all")
    Call<List<City>> getCities();

    //get fishes of cit
    @GET("fishes/all/city_id={id}&page={p}")
    Call<FishAd> getFishesOfCity(
            @Path("id") String city_id,
            @Path("p") int pageIndex
    );

    // get fishes types
    @GET("get/types")
    Call<FishesType> getFishesType();

    // get fishes types
    @GET("fishes/get_using_type/type_id={t}&city_id={c}&page={p}")
    Call<FishAd> getFishesWithSpesificType(
            @Path("t") int type_id,
            @Path("c") int city_id,
            @Path("p") int pageIndex
    );

    @GET("get/trips/for/marasi_id={m}&beach={b}&city_id={c}&page={p}")
    Call<TripAd> getAllTrips(
            @Path("m") String mrarsi_id,
            @Path("b") String beach,
            @Path("c") String city_id,
            @Path("p") int page


    );

    // log in user client
    @FormUrlEncoded
    @POST("user/login")
    Call<User> logInClient(@Field("email") String email, @Field("password") String password);

    // log in user privider
    @FormUrlEncoded
    @POST("customer/login")
    Call<User> logInProvider(@Field("email") String email, @Field("password") String password);

    // register user client
    @FormUrlEncoded
    @POST("user/register")
    Call<User> registerClient(
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("image") String image

    );

    // register user client
    @FormUrlEncoded
    @POST("customer/register")
    Call<User> registerProvider(
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("image") String image,
            @Field("city_id") String city,
            @Field("boatName") String boatName

    );

    // get all cities
    @GET("vehicle_types/all")
    Call<List<Boat>> getBoatsType();

    // get all cities
    @GET("marasi/all")
    Call<Marsa> getMarasi();


    // register user client
//    @Multipart
//    @POST("add/vehicle")
//    Call<VehicleResponce> addVehicle(
//            @Header("AuthKey") String AuthKey,
//            @Header("Content-Type") String Content_Type,
//            @Part("image") RequestBody photo,
//            @Part("c_id") String id,
//            @Part("v_vehicle_type_id") String v_vehicle_type_id,
//            @Part("boat_name") String boat_name,
//            @Part("v_register_number") String v_register_number,
//            @Part("v_des") String v_des,
//            @Part("marasi_id") String marasi_id
//    );

//    @Headers({"AuthKey: 0fce81f923b4cc70aadddd5b981e9df8194fb2121535557522", "Content-Type: multipart/form-data"})
//    @Multipart
//    @POST("add/vehicle")
//    Call<VehicleResponce> addVehicle(
//            @Part("c_id") RequestBody c_id,
//            @Part("v_vehicle_type_id") RequestBody v_vehicle_type_id,
//            @Part("boat_name") RequestBody boat_name,
//            @Part("v_register_number") RequestBody v_register_number,
//            @Part("v_des") RequestBody v_des,
//            @Part("marasi_id") RequestBody description,
//            @Part MultipartBody.Part file);

    @Multipart
    @POST("add/vehicle")
    Call<VehicleResponce> addVehicle(
            @Header("AuthKey") String header,
            @Part("c_id") int c_id,
            @Part("v_vehicle_type_id") int v_vehicle_type_id,
            @Part("boat_name") RequestBody boat_name,
            @Part("v_register_number") RequestBody v_register_number,
            @Part("v_des") String v_des,
            @Part("marasi_id") int marasi,
            @Part("image\"; filename=\"pp.png\" ") RequestBody file);


    @Headers({"AuthKey: 0fce81f923b4cc70aadddd5b981e9df8194fb2121535557522", "Content-Type: multipart/form-data"})
    @Multipart
    @POST("add/vehicle")
    Call<VehicleResponce> addVehicle(
            @Part("vehicle") RequestBody description,
            @Part MultipartBody.Part file);

    @Multipart
//    @Headers("AuthKey: 0fce81f923b4cc70aadddd5b981e9df8194fb2121535557522")
    @POST("add/trip/")
    Call<VehicleResponce> trip(
            @Header("AuthKey") String header,
            @Part("c_id") int c_id,
            @Part("t_vehicle_id") int t_vehicle_id,
            @Part("t_type_id") int t_type_id,
            @Part("t_date") String t_date,
            @Part("t_period_time") int t_period_time,
            @Part("t_start_time") RequestBody t_start_time,
            @Part("t_end_time") RequestBody t_end_time,
            @Part("city_id") int marasi_id,
            @Part("t_price") int price,
            @Part MultipartBody.Part[] surveyImage,
            @Part("services[]") List<RequestBody> services

    );

    @Multipart
    //  @Headers("AuthKey: 0fce81f923b4cc70aadddd5b981e9df8194fb2121535557522")
    @POST("add/fish/")
    Call<VehicleResponce> addFish(
            @Header("AuthKey") String header,
            @Part("c_id") int c_id,
            @Part("fish_delivery") int fish_delevary,
            @Part("fish_cooking") int fish_cooking,
            @Part("fish_type_id") int fish_type_id,
            @Part("fish_price") RequestBody fish_price,
            @Part("fish_weight") RequestBody fish_weight,
            @Part("fish_des") RequestBody fish_des,
            @Part("city_id") int marasi_id,
            @Part("fish_quantity") RequestBody fish_quantity,
            @Part MultipartBody.Part[] surveyImage
    );

    @GET("vehicles/all/user_id={id}")
    Call<Vehicle> getVehicles(@Path("id") String user_id);


    @GET("trips/all/customer_id={id}&page={p}")
    Call<TripAd> getAllTripsForUser(
            @Path("id") String customer_id,
            @Path("p") int page
    );

    @GET("fishes/all/customer_id={id}&page={p}")
    Call<FishAd> getFishesForUser(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    @GET("trip/periods")
    Call<Period> getPeriods();

    @GET("vehicles/all/user_id={id}")
    Call<Vehicle> getVehicleForUser(@Path("id") String customer_id);

    @GET("trip/types")
    Call<TripType> getTripsType();


    @GET("get/orders/trips_pending/user_id={id}&page={p}")
    Call<ProviderTripOrder> getPendingProviderOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    @GET("get/orders/trips_complete/user_id={id}&page={p}")
    Call<ProviderTripOrder> getcompletedProviderOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    @GET("get/orders/trips_rejected/user_id={id}&page={p}")
    Call<ProviderTripOrder> getRejectedProviderOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    //////////

    @GET("get/orders/fishes_pending/user_id={id}&page={p}")
    Call<ProviderFishOrder> getPendingProviderFishesOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    @GET("get/orders/fishes_complete/user_id={id}&page={p}")
    Call<ProviderFishOrder> getCompletedProviderFishesOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    @GET("get/orders/fishes_rejected/user_id={id}&page={p}")
    Call<ProviderFishOrder> getRejectedProviderFishesOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );


    ////////////////////////////////////////////////////////////////////////


    @GET("get/user/orders/trips_pending/user_id={id}&page={p}")
    Call<ProviderTripOrder> getPendingClientOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    @GET("get/user/orders/trips_complete/user_id={id}&page={p}")
    Call<ProviderTripOrder> getcompletedClientOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    @GET("get/user/orders/trips_rejected/user_id={id}&page={p}")
    Call<ProviderTripOrder> getRejectedClientOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    //////////

    @GET("get/user/orders/fishes_pending/user_id={id}&page={p}")
    Call<ProviderFishOrder> getPendingClientFishesOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    @GET("get/user/orders/fishes_complete/user_id={id}&page={p}")
    Call<ProviderFishOrder> getCompletedClientFishesOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    @GET("get/user/orders/fishes_rejected/user_id={id}&page={p}")
    Call<ProviderFishOrder> getRejectedClientFishesOrders(
            @Path("id") String customer_id,
            @Path("p") int pageIndex
    );

    @FormUrlEncoded
    @POST("add/fish_order")
    Call<BuyResponse> buyFishes(
            @Header("AuthKey") String header,
            @Field("user_id") String user_id,
            @Field("fish_id") String fish_id
    );

    @FormUrlEncoded
    @POST("add/trip_order")
    Call<BuyResponse> reserveTrip(
            @Header("AuthKey") String header,
            @Field("user_id") int user_id,
            @Field("trip_id") int fish_id,
            @Field("number_of_tickets") int number_of_tickets
    );


    @FormUrlEncoded
    @POST("trip/complete")
    Call<BuyResponse> acceptTrip(
            @Header("AuthKey") String header,
            @Field("order_id") String order_id
    );

    @FormUrlEncoded
    @POST("trip/refused?=")
    Call<BuyResponse> refuseTrip(
            @Header("AuthKey") String header,
            @Field("order_id") String order_id
    );

    @FormUrlEncoded
    @POST("fish/complete")
    Call<BuyResponse> acceptFish(
            @Header("AuthKey") String header,
            @Field("order_id") String order_id
    );

    @FormUrlEncoded
    @POST("fish/refused")
    Call<BuyResponse> refuseFish(
            @Header("AuthKey") String header,
            @Field("order_id") String order_id
    );

    @GET("get/trips/{id}")
    Call<SingleTripAd> getSingleAd(@Path("id") String id);
}