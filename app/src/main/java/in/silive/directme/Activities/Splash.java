package in.silive.directme.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import silive.in.sampledirectme.R;

/**
 * Created by akriti on 16/5/16.
 */
public class Splash extends AppCompatActivity {
    public int screenTimeOut = 3000;
    Context context;
    TextView no_net_connection;
    RelativeLayout splash;
    ImageView image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        splash = (RelativeLayout)findViewById(R.id.splash);
        context = getApplicationContext();
        image= (ImageView)findViewById(R.id.image);
        image.startAnimation(AnimationUtils.loadAnimation(this,R.anim.animate_splash));
        //no_net_connection = (TextView) findViewById(R.id.no_net_connection);
        checkConnection();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, screenTimeOut);
    }
    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            //no_net_connection.setVisibility(View.VISIBLE);
            Snackbar snackbar = Snackbar
                    .make(splash, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkConnection();
                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }

    }
}
