package com.Inspira.odo.sellerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.DataAdapter;
import com.Inspira.odo.adaptors.DataSellerHomeAdaptor;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.model.MyRequest;
import com.Inspira.odo.model.SellerHomeData;
import com.Inspira.odo.sellerData.CarDetails;
import com.Inspira.odo.sellerData.Order;
import com.Inspira.odo.sellerData.RelatedOrder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFavorites extends Fragment {


    RecyclerView recycler_view ;
    View rooteView ;
    private ArrayList<SellerHomeData> data;
    private DataAdapter dataSellerHomeAdaptor ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          View rooteViw =inflater.inflate(R.layout.fragment_my_favorites, container, false);
        getActivity().setTitle(R.string.My_Favorites);
        getActivity().findViewById(R.id.filter).setVisibility(View.VISIBLE);

        return rooteViw ;
    }


    private void initViews(){
        recycler_view = (RecyclerView)rooteView.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this.getActivity()));
        recycler_view.setLayoutManager(layoutManager);

        loadJSON();
    }

    private void loadJSON() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<RelatedOrder>> call = apiService.getRelatedOrder(new MyRequest("01009560622"));
        call.enqueue(new Callback<ArrayList<RelatedOrder>>() {
            @Override
            public void onResponse(Call<ArrayList<RelatedOrder>> call, Response<ArrayList<RelatedOrder>> response) {
                int responseCode = response.code();
                if (responseCode == 200) {
                    ArrayList<RelatedOrder> bankJSONResponse = response.body();
                    if (!bankJSONResponse.isEmpty()) {

                    }
                }


            }

            @Override
            public void onFailure(Call<ArrayList<RelatedOrder>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.My_Favorites);
    }

}
