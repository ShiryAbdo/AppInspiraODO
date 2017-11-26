
package com.Inspira.odo.sellerUi;


import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;
import com.Inspira.odo.adaptors.DataSellerHomeAdaptor;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.DataCar;
import com.Inspira.odo.data.Model.MyRequest;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.model.SellerHomeData;
import com.Inspira.odo.sellerData.FilterData;
import com.Inspira.odo.sellerData.RelatedOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;


public class SellerHome extends Fragment {

    RecyclerView recycler_view;
    View rooteView;
    ArrayList<String> categories_CarType, categories_car_model, categories_car_year;
    DataCar dataCar;
    ArrayList<RelatedOrder> MyOrderList;
    String itemTYear, item_model, itemType;
    Map<String, ArrayList<String>> AllData;
    SharedPreferencesManager sharedPreferencesManager;
    MyApplication myApplication;
    private ArrayList<SellerHomeData> datah;
    private DataSellerHomeAdaptor dataSellerHomeAdaptor;
    private FilterData data = new FilterData();
    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rooteView = inflater.inflate(R.layout.fragment_seller_home, container, false);
        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        Toast.makeText(getApplicationContext(), sharedPreferencesManager.getUser_Phoe(), Toast.LENGTH_LONG).show();
        datah = new ArrayList<>();
        getActivity().findViewById(R.id.filter).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showFilter();


            }
        });

        initViews();
        return rooteView;

    }

    private void initViews() {
        recycler_view = (RecyclerView) rooteView.findViewById(R.id.recycler_view);
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
                        MyOrderList = new ArrayList<RelatedOrder>();
                        MyOrderList.addAll(bankJSONResponse);

                        dataSellerHomeAdaptor = new DataSellerHomeAdaptor(MyOrderList, getActivity(), getActivity());
                        recycler_view.setAdapter(dataSellerHomeAdaptor);
                        data.setmList(MyOrderList);
                        myApplication = new MyApplication();
                        dataSellerHomeAdaptor.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "ResponseCode: " + responseCode, Toast.LENGTH_LONG).show();
//                        Log.d("CODE", "ResponseCode: " + responseCode);
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
        getActivity().setTitle(R.string.home_seller);
    }

    public void showFilter() {
        boolean check = false;
        final Dialog okdialog = new Dialog(getActivity(), R.style.custom_dialog_theme);
        okdialog.setContentView(R.layout.dialog_seller_home_data);
        Button OK_d = (Button) okdialog.findViewById(R.id.ok);
        Spinner SpinnerCarType = (Spinner) okdialog.findViewById(R.id.SpinnerCarType);
        Spinner your_car_model = (Spinner) okdialog.findViewById(R.id.your_car_model);
        Spinner your_car_year = (Spinner) okdialog.findViewById(R.id.your_car_year);
        ArrayList<String> categories;
        AllData = new HashMap<>();
        dataCar = new DataCar();
        CustomArrayAdapter_Spinner myAdaptor_CarType, myAdaptor_car_model, myAdaptor_year;
        categories_CarType = new ArrayList<String>();
        categories_car_year = new ArrayList<String>();
        categories_car_model = new ArrayList<String>();
        categories_CarType.add(getString(R.string.your_car_type));
        dataCar = new DataCar();
        AllData = dataCar.getAllData();
        dataCar.getCarTypesEnglish();
        categories_CarType.addAll(dataCar.getCarTypesEnglish());
        categories_CarType.add(getString(R.string.your_car_type));
        categories_car_model.add(getString(R.string.your_car_modle));
        categories_car_year.add(getString(R.string.your_car_year));
        categories_car_year.addAll(dataCar.getYears());


        myAdaptor_CarType = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_CarType);
        myAdaptor_car_model = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_car_model);
        myAdaptor_year = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_car_year);
        your_car_year.setAdapter(myAdaptor_year);
        SpinnerCarType.setAdapter(myAdaptor_CarType);
        your_car_model.setAdapter(myAdaptor_car_model);


        SpinnerCarType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                String itemTyp = parent.getItemAtPosition(position).toString();
                if (itemTyp.equals(getString(R.string.your_car_type))) {
                    itemType = null;
//                    Toast.makeText(parent.getContext(),getString(R.string.chose_type) , Toast.LENGTH_LONG).show();
                } else {
                    itemType = parent.getItemAtPosition(position).toString();
                    categories_car_model.clear();
                    categories_car_model.add(getString(R.string.your_car_modle));
                    categories_car_model.addAll(AllData.get(itemType));


//                    Toast.makeText(parent.getContext(), itemType, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        myAdaptor_car_model = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_car_model);
        your_car_model.setAdapter(myAdaptor_car_model);

        your_car_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                String item_mode = parent.getItemAtPosition(position).toString();
                if (item_mode.equals(getString(R.string.your_car_modle))) {
                    item_model = null;
//                    Toast.makeText(parent.getContext(),getString(R.string.chose_modle) , Toast.LENGTH_LONG).show();
                } else {

                    item_model = parent.getItemAtPosition(position).toString();
                    //                    Toast.makeText(parent.getContext(), item_model, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        your_car_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                String itemTYea = parent.getItemAtPosition(position).toString();
                if (itemTYea.equals(getString(R.string.your_car_year))) {
                    itemTYear = null;
//                    Toast.makeText(parent.getContext(),getString(R.string.chose_modle) , Toast.LENGTH_LONG).show();
                } else {
                    itemTYear = parent.getItemAtPosition(position).toString();
                    //                    Toast.makeText(parent.getContext(), itemTYear, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        OK_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item_model != null && itemTYear != null && itemType != null) {
                    addToSelectedMap("itemType", itemType);
                    addToSelectedMap("item_model", item_model);
                    addToSelectedMap("itemTYear", itemTYear);
                    if (data != null) {
                        ArrayMap<String, List<String>> applied_filter = applied_filters;
                        if (applied_filter.size() != 0) {
                            List<RelatedOrder> filteredList = data.getAllData();
                            //iterate over arraymap
                            for (Map.Entry<String, List<String>> entry : applied_filter.entrySet()) {
                                Log.d("k9res", "entry.key: " + entry.getKey());
                                switch (entry.getKey()) {
                                    case "itemType":
                                        filteredList = data.getCarType(entry.getValue(), filteredList);
                                        break;
                                    case "item_model":
                                        filteredList = data.getModel(entry.getValue(), filteredList);
                                        break;
                                    case "itemTYear":
                                        filteredList = data.getYear(entry.getValue(), filteredList);
                                        break;

                                }
                                dataSellerHomeAdaptor.clear();
                                dataSellerHomeAdaptor.addAll(filteredList);
                            }
                        }
                    }

                } else {

                    Toast.makeText(getActivity(), "chose data", Toast.LENGTH_LONG).show();
                }
                okdialog.dismiss();
            }
        });
        okdialog.show();


    }

    private void addToSelectedMap(String key, String value) {
        if (applied_filters.get(key) != null && !applied_filters.get(key).contains(value)) {
            applied_filters.get(key).add(value);
        } else {
            List<String> temp = new ArrayList<>();
            temp.add(value);
            applied_filters.put(key, temp);
        }
    }


}