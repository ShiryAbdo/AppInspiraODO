package com.Inspira.odo.mainLuncher;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.Inspira.odo.R;
import com.Inspira.odo.helper.LocaleHelper;


public class ChangeLanguage extends Fragment implements View.OnClickListener {


    Button arabic, english;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_change_language, container, false);
        getActivity().setTitle(R.string.Change_Language);
        getActivity().findViewById(R.id.filter).setVisibility(View.GONE);

        arabic = (Button) rootView.findViewById(R.id.arabic);
        english = (Button) rootView.findViewById(R.id.english);
        arabic.setOnClickListener(this);
        english.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.arabic:
                toggleArabic();
                break;
            case R.id.english:
                toggleEnglish();
                break;

        }
    }

    private void toggleArabic(){
        LocaleHelper.setLocale(getActivity(),"ar");
        getActivity().startActivity(new Intent(getActivity(),LogInActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        getActivity().finish();
    }

    private void toggleEnglish(){
        LocaleHelper.setLocale(getActivity(),"en");
        getActivity().startActivity(new Intent(getActivity(),LogInActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        getActivity().finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getActivity().setTitle(R.string.Change_Language);

    }
}
