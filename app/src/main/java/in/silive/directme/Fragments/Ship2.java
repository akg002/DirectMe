package in.silive.directme.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import in.silive.directme.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ship2 extends Fragment implements View.OnClickListener {
    ImageView bamboo,coconut,timber,banana;
    Button goto_inventory;


    public Ship2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ship1, container, false);
        bamboo = (ImageView)view.findViewById(R.id.bamboo);
        bamboo.setOnClickListener(this);
        coconut = (ImageView)view.findViewById(R.id.coconut);
        coconut.setOnClickListener(this);
        timber = (ImageView)view.findViewById(R.id.timber);
        timber.setOnClickListener(this);
        banana = (ImageView)view.findViewById(R.id.banana);
        banana.setOnClickListener(this);
        goto_inventory = (Button)view.findViewById(R.id.goto_inventory);
        goto_inventory.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bamboo:
                Toast.makeText(getContext(),"-50 Bamboo units",Toast.LENGTH_SHORT).show();
                break;
            case R.id.coconut:
                Toast.makeText(getContext(),"-50 Coconut units",Toast.LENGTH_SHORT).show();
                break;
            case R.id.timber:
                Toast.makeText(getContext(),"-50 Timber units",Toast.LENGTH_SHORT).show();
                break;
            case R.id.banana:
                Toast.makeText(getContext(),"-50 Banana units",Toast.LENGTH_SHORT).show();
                break;
            case R.id.goto_inventory:
                Intent i = new Intent(getContext(),Inventory.class);
                startActivity(i);
        }

    }

}
