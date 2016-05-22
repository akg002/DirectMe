package in.silive.directme.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import in.silive.directme.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button upgrade, park;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        sp = getSharedPreferences("DirectMe",MODE_PRIVATE);
        upgrade = (Button)findViewById(R.id.upgrade);
        upgrade.setOnClickListener(this);
        park = (Button)findViewById(R.id.park);
        park.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent ;
        switch (v.getId()){
            case R.id.upgrade: intent = new Intent(this,Shop.class);
                startActivity(intent);
                break;
            case R.id.park:
                intent = new Intent(this,Map.class);
                startActivity(intent);
                break;
        }
    }

    public void exitGame(View v){
        finish();
        System.exit(0);
    }


    public void logout(View v){
        FacebookSdk.sdkInitialize(getApplicationContext());
        LoginManager.getInstance().logOut();
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("login",false);
        editor.apply();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
