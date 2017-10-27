package com.Inspira.odo.mainLuncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
 import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity {
    LoginButton loginButton ;
    CallbackManager callbackManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_log_in);

        callbackManager=CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("email","public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String usrrId = loginResult.getAccessToken().getUserId();
//                GraphRequest  graphRequest  =GraphRequest.newGraphPathRequest(loginResult.getAccessToken(),new )

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        displayUserInfo(object);

                    }
                });
                Bundle  pramaet =new Bundle();
                pramaet.putString("fields","first_name, last_name,id");
                graphRequest.setParameters(pramaet);
                graphRequest.executeAsync();


            }


            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    private void displayUserInfo(JSONObject object) {
        String first_name , last_name , id , email;
        first_name="";
        last_name="";
        id="";
        email="";
        try {
            first_name =object.getString("first_name");
            last_name=object.getString("last_name");
            email=object.getString("email");
            id= object.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(),first_name +"first_name",Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),last_name +"last_name",Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),email +"email",Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),id +"id",Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
