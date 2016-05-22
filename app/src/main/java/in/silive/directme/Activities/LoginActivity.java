package in.silive.directme.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.util.Arrays;

import in.silive.directme.R;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener ,View.OnClickListener{
    LoginButton fbLoginBtn;
    CallbackManager fbCallBackManager ;
    GoogleApiClient mGoogleApiClient ;
    SignInButton googleSignInBtn ;
    final int RC_SIGN_IN = 9231;
    static final String TAG = "DirectMe";
    RelativeLayout loginFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_login);
        loginFrame = (RelativeLayout)findViewById(R.id.login_frame);
       setUpFbLogin();
        setUpGoogleLogin();
    }

    //Google Sign In
    void setUpGoogleLogin(){
        googleSignInBtn = (SignInButton)findViewById(R.id.google_sign_in_btn);
        googleSignInBtn.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* Activity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            //Toast.makeText(this,"Sign in success"+acct.getDisplayName(),Toast.LENGTH_LONG).show();
            loggedInSuccessfully(acct.getDisplayName(),acct.getEmail());
        } else {
            Toast.makeText(this,"Sign in failed",Toast.LENGTH_LONG).show();
        }
    }

    //Facebook LogIN
    void setUpFbLogin(){
        AppEventsLogger.activateApp(getApplication());
        fbCallBackManager = CallbackManager.Factory.create();
        fbLoginBtn = (LoginButton)findViewById(R.id.facebook_login_btn);
        fbLoginBtn.setReadPermissions(Arrays.asList("public_profile","email"));
        fbLoginBtn.registerCallback(fbCallBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken token = loginResult.getAccessToken();
                //Getting Profile Info using token
                GraphRequest request = GraphRequest.newMeRequest(
                        token,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                try {
                                    loggedInSuccessfully(object.getString("name"),object.getString("email"));
                                }catch (Exception e){
                                   loginFailed();
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
loginFailed();
            }
        });
    }

    //Method called when login is successful
    void loggedInSuccessfully(String name,String email){
        Toast.makeText(LoginActivity.this, "Logged in as "+name, Toast.LENGTH_LONG).show();
        SharedPreferences sp = getSharedPreferences("DirectMe",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("login",true);
        editor.putString("fullname",name);
        editor.putString("email",email);
        editor.apply();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void loginFailed(){
        Toast.makeText(this, "Failed to log in.", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fbCallBackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
loginFailed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.google_sign_in_btn :
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
        }
    }
}
