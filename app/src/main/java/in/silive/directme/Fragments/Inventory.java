package in.silive.directme.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import in.silive.directme.Adapters.InventoryListAdapter;
import in.silive.directme.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inventory extends Fragment {
    ListView item_list;
    String[] nameOfItems = {"A stack of Bamboos/n100 Coins", "A cluster of Coconuts/n 150 Coins", "A tag of Timber/n175 Coins", "A bunch of Bananas/n200 Coins"};
    Integer[] imageOfItems = {R.drawable.bamboo, R.drawable.coconut, R.drawable.timber, R.drawable.timber};


    public Inventory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        item_list = (ListView) view.findViewById(R.id.item_list);
        item_list.setAdapter(new InventoryListAdapter(getContext(), nameOfItems, imageOfItems));
        return view;
    }

}
