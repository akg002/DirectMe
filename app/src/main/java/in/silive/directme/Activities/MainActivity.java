package in.silive.directme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import in.silive.directme.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button upgrade, park;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
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
                break;
        }
    }
}
