package com.Inspira.odo.data;

import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.data.Model.MyRequest;
import com.Inspira.odo.data.Model.Order;
import com.Inspira.odo.data.Model.SellerRegistration;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    String Register = "signup/register/ ";

    String Orders = "orders/buyer/addOrder";

    String UploadImage = "/images/upload ";
    String Login = "/login/" ;
    String myOrders ="/profile/buyer/myOrders" ;



    @POST(Register)
    Call<ResponseBody> doBuyerRegister(@Body BuyerRegistration registration) ;
    @POST(myOrders)
    Call<ResponseBody> domyOrders(@Body MyRequest myRequest) ;

    @POST(Register)
    Call<ResponseBody> doSellerRegister(@Body SellerRegistration registration) ;

    @POST(Orders)
    Call<ResponseBody> addOrders(@Body Order order);

    @Multipart
    @POST(UploadImage)
    Call<Response> uploadImage(@Part MultipartBody.Part photos);
}

