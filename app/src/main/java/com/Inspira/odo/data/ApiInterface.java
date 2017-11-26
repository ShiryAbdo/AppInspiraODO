package com.Inspira.odo.data;

import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.data.Model.Login;
import com.Inspira.odo.data.Model.LoginData;
import com.Inspira.odo.data.Model.MakOrder;
import com.Inspira.odo.database.MyOrder;
import com.Inspira.odo.data.Model.MyRequest;
import com.Inspira.odo.data.Model.SellerRegistration;
import com.Inspira.odo.model.UploadResponse;
import com.Inspira.odo.sellerData.RelatedOrder;
import com.Inspira.odo.sellerData.RespondToOrder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    String Register = "signup/register/ ";

    String Orders = "orders/buyer/addOrder";

    String UploadImage = "/images/upload ";
    String login = "/login/" ;
    String myOrders ="/profile/buyer/myOrders" ;
    String RelatedOrders ="/orders/seller/relatedOrders" ;
    String RespondtoaReques ="/respondToOrder";



    @POST(Register)
    Call<ResponseBody> doBuyerRegister(@Body BuyerRegistration registration) ;
    @POST(myOrders)
    Call<ArrayList<MyOrder>> domyOrders(@Body MyRequest myRequest) ;
    @POST(login)
    Call<LoginData> getLogin(@Body Login Login);

    @POST(Register)
    Call<ResponseBody> doSellerRegister(@Body SellerRegistration registration) ;

    @POST(Orders)
    Call<ResponseBody> addOrders(@Body MakOrder order);

    @Multipart
    @POST(UploadImage)
    Call<List<UploadResponse>> uploadImage(@Part MultipartBody.Part photos);


//    ******* seler


    @POST(RelatedOrders)
    Call<ArrayList<RelatedOrder>> getRelatedOrder(@Body MyRequest myRequest) ;
    @POST(RespondtoaReques)
    Call<ResponseBody> doRespondtoaReques(@Body RespondToOrder respondToOrder);
}

