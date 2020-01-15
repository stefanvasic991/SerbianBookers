package com.easyswitch.serbianbookers;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityBody;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.Event;
import com.easyswitch.serbianbookers.models.GuestList;
import com.easyswitch.serbianbookers.models.GuestNotShow;
import com.easyswitch.serbianbookers.models.InsertAvail;
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.InsertRestriction;
import com.easyswitch.serbianbookers.models.News;
import com.easyswitch.serbianbookers.models.ReservationFilter;
import com.easyswitch.serbianbookers.models.ReservationList;
import com.easyswitch.serbianbookers.models.Restriction;
import com.easyswitch.serbianbookers.models.Search;
import com.easyswitch.serbianbookers.models.ShowCard;
import com.easyswitch.serbianbookers.models.Statistics;
import com.easyswitch.serbianbookers.models.User;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class WebApiClient extends AndroidViewModel {

    private WebApi webApi = WebApiManager.get(getApplication()).getWebApi();

    private MutableLiveData<User> login;
    private MutableLiveData<Data> data;
    private MutableLiveData<ReservationList> reservation;
    private MutableLiveData<GuestList> guests;
    private MutableLiveData<Search> search;
    private MutableLiveData<ReservationFilter> filter;
    private MutableLiveData<Availability> availability;
    private MutableLiveData<Restriction> restrictions;
    private MutableLiveData<News> news;
    private MutableLiveData<Event> events;
    private MutableLiveData<InsertAvail> insertAvail;
    private MutableLiveData<InsertPrice> insertPrice;
    private MutableLiveData<InsertRestriction> insertRestriction;
    private MutableLiveData<GuestNotShow> guestNotShow;
    private MutableLiveData<GuestNotShow> invalidCard;
    private MutableLiveData<ShowCard> showCard;
    private MutableLiveData<Statistics> statistics;

    public WebApiClient(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<User> getLogin(User user) {
        if (login == null) {
            login = new MutableLiveData<>();
            webApi.login(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        login.setValue(response.body());
                    } else {
                        login.setValue(null);
                        Timber.v("ResponseError");
                    }
                }
//
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    t.printStackTrace();
                    login.setValue(null);
                }
            });
        }
        return login;
    }

    public MutableLiveData<Data> getData(DataBody dataBody) {
        if (data == null) {
            data = new MutableLiveData<>();
            webApi.data(dataBody).enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    if (response.isSuccessful()) {
                        data.setValue(response.body());
                    } else {
                        data.setValue(null);
                        Timber.v("ResponseError");
//                        switch (response.code()) {
//                            case 404:
//                                Timber.v( "not found");
//                                break;
//                            case 500:
//                                Timber.v("server broken");
//                                break;
//                            default:
//                                Timber.v("unknown error");
//                                break;
//                        }
                    }
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    t.printStackTrace();
                    Timber.v("onFailure");
                }
            });
        }
        return data;
    }

    public MutableLiveData<ReservationList> getReservation(ReservationList reservationList) {
        if (reservation == null) {
            reservation = new MutableLiveData<>();
            webApi.reservation(reservationList).enqueue(new Callback<ReservationList>() {
                @Override
                public void onResponse(Call<ReservationList> call, Response<ReservationList> response) {
                    if (response.isSuccessful()) {
                        reservation.setValue(response.body());
                    } else {
                        reservation.setValue(null);
                        Timber.e("OnResponse Error!");
                    }
                }

                @Override
                public void onFailure(@NotNull Call<ReservationList> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
//                    reservation.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return reservation;
    }

    public MutableLiveData<GuestList> getGuests(GuestList guestList) {
        if (guests == null) {
            guests = new MutableLiveData<>();
            webApi.guests(guestList).enqueue(new Callback<GuestList>() {
                @Override
                public void onResponse(Call<GuestList> call, Response<GuestList> response) {
                    if (response.isSuccessful()) {
                        guests.setValue(response.body());
                    } else {
                        guests.setValue(null);
                    }
                }

                @Override
                public void onFailure(Call<GuestList> call, Throwable t) {
//                    guests.setValue(null);
                    Log.e("Greska", t.getMessage());
                    Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
        }
        return guests;
    }

    public MutableLiveData<Search> getSearch(Search s) {
        if (search == null) {
            search = new MutableLiveData<>();
            webApi.search(s).enqueue(new Callback<Search>() {
                @Override
                public void onResponse(Call<Search> call, Response<Search> response) {
                    if (response.isSuccessful()) {
                        search.setValue(response.body());
                        Timber.v("search radi");
                    } else {
                        search.setValue(null);
                        Timber.v("search ovde puca");
                    }
                }

                @Override
                public void onFailure(Call<Search> call, Throwable t) {
                    t.printStackTrace();
                    search.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return search;
    }

    public MutableLiveData<ReservationFilter> getFilter(ReservationFilter rf) {
        if (filter == null) {
            filter = new MutableLiveData<>();

            webApi.reservationFilter(rf).enqueue(new Callback<ReservationFilter>() {
                @Override
                public void onResponse(Call<ReservationFilter> call, Response<ReservationFilter> response) {
                    if (response.isSuccessful()) {
                        filter.setValue(response.body());
                    } else {
                        filter.setValue(null);
                        Timber.v("onResponse");
                    }
                }

                @Override
                public void onFailure(Call<ReservationFilter> call, Throwable t) {
                    t.printStackTrace();
                    filter.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return filter;
    }

    public MutableLiveData<Availability> getAvailability(Availability a) {
        if (availability == null) {
            availability = new MutableLiveData<>();
            webApi.availability(a).enqueue(new Callback<Availability>() {
                @Override
                public void onResponse(Call<Availability> call, Response<Availability> response) {
                    if (response.isSuccessful()) {
                        availability.setValue(response.body());
                    } else {
                        availability.setValue(null);
                    }
                }

                @Override
                public void onFailure(Call<Availability> call, Throwable t) {
                    t.printStackTrace();
                    availability.setValue(null);
                    Timber.v("onFailure");

                }
            });
        }
        return availability;
    }

    public MutableLiveData<Restriction> getRestrictions(Restriction restriction) {
        if (restrictions == null) {
            restrictions = new MutableLiveData<>();
            webApi.restriction(restriction).enqueue(new Callback<Restriction>() {
                @Override
                public void onResponse(Call<Restriction> call, Response<Restriction> response) {
                    if (response.isSuccessful()) {
                        restrictions.setValue(response.body());
                    } else {
                        restrictions.setValue(null);
                    }
                }

                @Override
                public void onFailure(Call<Restriction> call, Throwable t) {
                    t.printStackTrace();
                    restrictions.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return restrictions;
    }

    public MutableLiveData<News> getNews(News n) {
        if (news == null) {
            news = new MutableLiveData<>();

            webApi.news(n).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    if (response.isSuccessful()) {
                        news.setValue(response.body());
                    } else {
                        news.setValue(null);
                        Timber.v("ResponseError");
                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    t.printStackTrace();
                    news.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return news;
    }

    public MutableLiveData<Event> getEvents(Event event) {
        if (events == null) {
            events = new MutableLiveData<>();

            webApi.event(event).enqueue(new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    if (response.isSuccessful()) {
                        events.setValue(response.body());
                    } else {
                        events.setValue(null);
                        Timber.v("onResponse");
                    }
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {
                    t.printStackTrace();
                    events.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return events;
    }

    public MutableLiveData<InsertAvail> getInsertAvail(InsertAvail avail) {
        if (insertAvail == null) {
            insertAvail = new MutableLiveData<>();
            webApi.insertAvail(avail).enqueue(new Callback<InsertAvail>() {
                @Override
                public void onResponse(Call<InsertAvail> call, Response<InsertAvail> response) {
                    if (response.isSuccessful()) {
                        insertAvail.setValue(response.body());
                    } else {
                        insertAvail.setValue(null);
                        Timber.v("OnResponse");
                    }
                }

                @Override
                public void onFailure(Call<InsertAvail> call, Throwable t) {
                    t.printStackTrace();
                    insertAvail.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return insertAvail;
    }

    public MutableLiveData<InsertPrice> getInsertPrice(InsertPrice price) {
        if (insertPrice == null) {
            insertPrice = new MutableLiveData<>();
            webApi.insertPrice(price).enqueue(new Callback<InsertPrice>() {
                @Override
                public void onResponse(Call<InsertPrice> call, Response<InsertPrice> response) {
                    if (response.isSuccessful()) {
                        insertPrice.setValue(response.body());
                    } else {
                        insertPrice.setValue(null);
                        Timber.v("OnResponse");
                    }
                }

                @Override
                public void onFailure(Call<InsertPrice> call, Throwable t) {
                    t.printStackTrace();
                    insertPrice.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return insertPrice;
    }

    public MutableLiveData<InsertRestriction> getInsertRestriction(InsertRestriction ir) {
        if (insertRestriction == null) {
            insertRestriction = new MutableLiveData<>();

            webApi.insertRestriction(ir).enqueue(new Callback<InsertRestriction>() {
                @Override
                public void onResponse(Call<InsertRestriction> call, Response<InsertRestriction> response) {
                    if (response.isSuccessful()) {
                        insertRestriction.setValue(response.body());
                    } else {
                        insertRestriction.setValue(null);
                        Timber.v("onResponse");
                    }
                }

                @Override
                public void onFailure(Call<InsertRestriction> call, Throwable t) {
                    t.printStackTrace();
                    insertRestriction.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return insertRestriction;
    }

    public MutableLiveData<GuestNotShow> getGuestNotShow(GuestNotShow notShow) {
        if (guestNotShow ==  null) {
            guestNotShow = new MutableLiveData<>();

            webApi.noShow(notShow).enqueue(new Callback<GuestNotShow>() {
                @Override
                public void onResponse(Call<GuestNotShow> call, Response<GuestNotShow> response) {
                    if (response.isSuccessful()) {
                        guestNotShow.setValue(response.body());
                    } else {
                        guestNotShow.setValue(null);
                        Timber.v("OnResponse");
                    }
                }

                @Override
                public void onFailure(Call<GuestNotShow> call, Throwable t) {
                    t.printStackTrace();
                    guestNotShow.setValue(null);
                    Timber.v("OnFailure");
                }
            });
        }
        return guestNotShow;
    }

    public MutableLiveData<GuestNotShow> getInvalidCard(GuestNotShow notShow) {
        if (invalidCard ==  null) {
            invalidCard = new MutableLiveData<>();

            webApi.invalidCard(notShow).enqueue(new Callback<GuestNotShow>() {
                @Override
                public void onResponse(Call<GuestNotShow> call, Response<GuestNotShow> response) {
                    if (response.isSuccessful()) {
                        invalidCard.setValue(response.body());
                    } else {
                        invalidCard.setValue(null);
                        Timber.v("OnResponse");
                    }
                }

                @Override
                public void onFailure(Call<GuestNotShow> call, Throwable t) {
                    t.printStackTrace();
                    invalidCard.setValue(null);
                    Timber.v("OnFailure");
                }
            });
        }
        return invalidCard;
    }

    public MutableLiveData<ShowCard> getShowCard(ShowCard sc) {
        if (showCard == null) {
            showCard = new MutableLiveData<>();

            webApi.showCard(sc).enqueue(new Callback<ShowCard>() {
                @Override
                public void onResponse(Call<ShowCard> call, Response<ShowCard> response) {
                    if (response.isSuccessful()) {
                        showCard.setValue(response.body());
                    } else {
                        showCard.setValue(null);
                        Timber.v("onResponse");
                    }
                }

                @Override
                public void onFailure(Call<ShowCard> call, Throwable t) {
                    t.printStackTrace();
                    showCard.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return showCard;
    }

    public MutableLiveData<Statistics> getStatistics(Statistics stats) {
        if (statistics == null) {
            statistics = new MutableLiveData<>();

            webApi.statistics(stats).enqueue(new Callback<Statistics>() {
                @Override
                public void onResponse(Call<Statistics> call, Response<Statistics> response) {
                    if (response.isSuccessful()) {
                        statistics.setValue(response.body());
                    } else {
                        statistics.setValue(null);
                        Timber.v("onResponse");
                    }
                }

                @Override
                public void onFailure(Call<Statistics> call, Throwable t) {
                    t.printStackTrace();
                    statistics.setValue(null);
                    Timber.v("onFailure");
                }
            });
        }
        return statistics;
    }
}
