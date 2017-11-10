package com.Inspira.odo.data;

import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.data.Model.Order;
import com.Inspira.odo.data.Model.SellerRegistration;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    String Register = "signup/register/ ";

    String Orders = "orders/buyer/addOrder";

    String UploadImage = "/images/upload ";



    @POST(Register)
    Call<ResponseBody> doBuyerRegister(@Body BuyerRegistration registration) ;

    @POST(Register)
    Call<ResponseBody> doSellerRegister(@Body SellerRegistration registration) ;

    @POST(Orders)
    Call<ResponseBody> addOrders(@Body Order order);

}