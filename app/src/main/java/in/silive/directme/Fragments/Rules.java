package in.silive.directme.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import in.silive.directme.Adapters.RulesAdapter;
import in.silive.directme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rules extends Fragment {
    String[] game_rules;
    ListView list_rules;


    public Rules() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_rules, container, false);
        //game_rules = getResources().getStringArray();
        list_rules = (ListView) view.findViewById(R.id.list_rules);
        list_rules.setAdapter(new RulesAdapter(getContext(), game_rules));
        return view;
    }

}
