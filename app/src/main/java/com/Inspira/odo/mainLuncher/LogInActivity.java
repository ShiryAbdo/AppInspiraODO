package com.Inspira.odo.mainLuncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.sellerUi.ContinuingRegSeler;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LogInActivity extends AppCompatActivity {
    LoginButton loginButton ;
    CallbackManager callbackManager ;
    Button signUP ;
    Button login ;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_log_in);
        signUP =(Button)findViewById(R.id.signUP);
        login =(Button)findViewById(R.id.login);

        callbackManager=CallbackManager.Factory.create();
        // Add code to print out the key hash
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
//                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.facebook.samples.hellofacebook",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("email","public_profile");
         loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String usrrId = loginResult.getAccessToken().getUserId();

                AccessToken accessToken = loginResult.getAccessToken();
//                GraphRequest  graphRequest  =GraphRequest.newGraphPathRequest(loginResult.getAccessToken(),new )

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        displayUserInfo(object);


                    }
                });
                Bundle  pramaet =new Bundle();
                pramaet.putString("fields","first_name, last_name,email,id" );
                graphRequest.setParameters(pramaet);
                graphRequest.executeAsync();


            }


            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"erro",Toast.LENGTH_SHORT).show();


            }
        });

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this,RegistrationActivity.class);
                startActivity(intent);
                finish();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this,SinInRegis.class);
                startActivity(intent);
                finish();
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

        if (first_name!=null&&email!=null&&last_name!=null){
            String  user_name = first_name+last_name;
            if(user_name!=null){
                Intent intent = new Intent(LogInActivity.this, ContinueRigeWithFacebook.class);
                intent.putExtra("user_name",user_name);
                intent.putExtra("email",email);
                startActivity(intent);
            }


        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
