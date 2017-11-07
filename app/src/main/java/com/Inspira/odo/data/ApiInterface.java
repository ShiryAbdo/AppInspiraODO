package com.Inspira.odo.data;

import com.Inspira.odo.data.Model.BuyerRegistration;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    String Register = "signup/register/ ";

    @POST(Register)
    Call<ResponseBody> doBuyerRegister(@Body BuyerRegistration registration) ;


}