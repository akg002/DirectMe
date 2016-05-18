package in.silive.directme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import in.silive.directme.R;

/**
 * Created by akriti on 17/5/16.
 */
public class RulesAdapter extends BaseAdapter {
    String[] rules;
    public static LayoutInflater inflater = null;
    Context c;
    public class Holder{
        TextView list_of_rules;
    }

    public RulesAdapter(Context context, String[] rules) {
        c = context;
        this.rules = rules;
    }

    @Override
    public int getCount() {
        return rules.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View view ;
        view = inflater.inflate(R.layout.fragment_rules, null);
        holder.list_of_rules = (TextView)view.findViewById(R.id.list_of_rules);
        holder.list_of_rules.setText(rules[position]);
        return view;
    }
}
