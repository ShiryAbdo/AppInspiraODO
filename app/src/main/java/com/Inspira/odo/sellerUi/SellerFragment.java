package com.Inspira.odo.sellerUi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.Inspira.odo.R;

import static com.Inspira.odo.util.HashBuilder.md5;
import static com.Inspira.odo.util.UserRegistrationPref.USER_EMAIL;
import static com.Inspira.odo.util.UserRegistrationPref.USER_FUll_NAME;
import static com.Inspira.odo.util.UserRegistrationPref.USER_HASH_VALUE;
import static com.Inspira.odo.util.UserRegistrationPref.USER_PASSWORD;
import static com.Inspira.odo.util.UserRegistrationPref.USER_PHONE_NO;
import static com.Inspira.odo.util.UserRegistrationPref.persist;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellerFragment extends Fragment {


    Button CreatCampanyAcount ;
    EditText fName, phoneNo, password, email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rooteView = inflater.inflate(R.layout.fragment_seller, container, false);
        getActivity().setTitle("Home");
        CreatCampanyAcount = (Button) rooteView.findViewById(R.id.CreatCampanyAcount);
        fName = (EditText) rooteView.findViewById(R.id.fname);
        phoneNo = (EditText)rooteView.findViewById(R.id.phone_no);
        password =(EditText) rooteView.findViewById(R.id.password);
        email = (EditText)rooteView.findViewById(R.id.email);
        CreatCampanyAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean value = true;
                if (fName.getText().toString().isEmpty()){
                    value = false;
//                    fName.setError(getActivity().getResources().getString(R.string.required));
                    fName.requestFocus();
                }else if (phoneNo.getText().toString().isEmpty()) {
                    value = false;
//                    phoneNo.setError(getActivity().getResources().getString(R.string.required));
                    phoneNo.requestFocus();
                }else if (password.getText().toString().isEmpty()) {
                    value = false;
//                    password.setError(getActivity().getResources().getString(R.string.required));
                    password.requestFocus();
                }else if (email.getText().toString().isEmpty()) {
                    value = false;
//                    email.setError(getActivity().getResources().getString(R.string.required));
                    email.requestFocus();
                }
                if (value) {
                    persist(getActivity(), USER_FUll_NAME, fName.getText().toString());
                    persist(getActivity(), USER_PHONE_NO, phoneNo.getText().toString());
                    persist(getActivity(), USER_PASSWORD, password.getText().toString());
                    persist(getActivity(), USER_HASH_VALUE, md5(password.getText().toString()));
                    persist(getActivity(), USER_EMAIL, email.getText().toString());
                    Intent intent = new Intent(getActivity(), ContinuingRegSeler.class);
                    startActivity(intent);
                }
            }
        });


        return rooteView;
    }

}
