package com.Inspira.odo.data;

import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.data.Model.Order;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by shirya on 08/11/17.
 */

public interface ApiInterfaceOrder {

    String Orders = "orders/buyer/addOrder";

    @POST(Orders)
    Call<ResponseBody> doOrders(@Body Order order) ;

}


