package com.easyswitch.serbianbookers;

import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.GuestList;
import com.easyswitch.serbianbookers.models.GuestNotShow;
import com.easyswitch.serbianbookers.models.InsertAvail;
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.News;
import com.easyswitch.serbianbookers.models.ReservationList;
import com.easyswitch.serbianbookers.models.Restriction;
import com.easyswitch.serbianbookers.models.Search;
import com.easyswitch.serbianbookers.models.ShowCard;
import com.easyswitch.serbianbookers.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebApi {

    String BASE_URL = "https://admin.serbian-bookers.com/";
    int TIMEOUT = 40;

    @Headers("Content-type: application/json")
    @POST("api/account/login")
    Call<User> login(@Body User user);

    @Headers("Content-type: application/json")
    @POST("api/data/all")
    Call<Data> data(@Body DataBody dataBody);

    @Headers("Content-type: application/json")
    @POST("api/data/reservations")
    Call<ReservationList> reservation(@Body ReservationList reservationList);

    @Headers("Content-type: application/json")
    @POST("api/data/guests")
    Call<GuestList> guests(@Body GuestList guestList);

    @Headers("Content-type: application/json")
    @POST("api/data/search")
    Call<Search> search(@Body Search search);

    @Headers("Content-type: application/json")
    @POST("api/data/avail")
    Call<Availability> availability(@Body Availability a);

    @Headers("Content-type: application/json")
    @POST("api/data/restrictions")
    Call<Restriction> restriction(@Body Restriction restriction);

    @Headers("Content-type: application/json")
    @POST("api/data/news")
    Call<News> news(@Body News news);



    @Headers("Content-type: application/json")
    @POST("api/data/insert/avail")
    Call<InsertAvail> insertAvail(@Body InsertAvail insertAvail);

    @Headers("Content-type: application/json")
    @POST("api/data/insert/price")
    Call<InsertPrice> insertPrice(@Body InsertPrice insertPrice);

    @Headers("Content-type: application/json")
    @POST("api/user/noshow")
    Call<GuestNotShow> noShow(@Body GuestNotShow guestNotShow);

    @Headers("Content-type: application/json")
    @POST("api/user/invalidcc")
    Call<GuestNotShow> invalidCard(@Body GuestNotShow guestNotShow);

    @Headers("Content-type: application/json")
    @POST("api/data/showCC")
    Call<ShowCard> showCard(@Body ShowCard showCard);
}
