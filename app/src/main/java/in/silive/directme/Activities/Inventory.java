package in.silive.directme.Activities;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import in.silive.directme.Adapters.InventoryListAdapter;
import in.silive.directme.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inventory extends AppCompatActivity {
    ListView item_list;
    String[] nameOfItems = {"A stack of Bamboos/n100 Coins", "A cluster of Coconuts/n 150 Coins", "A tag of Timber/n175 Coins", "A bunch of Bananas/n200 Coins"};
    Integer[] imageOfItems = {R.drawable.bamboo, R.drawable.coconut, R.drawable.timber, R.drawable.timber};


    public Inventory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inventory);
        item_list = (ListView) findViewById(R.id.item_list);
        item_list.setAdapter(new InventoryListAdapter(this, nameOfItems, imageOfItems));
    }


}
