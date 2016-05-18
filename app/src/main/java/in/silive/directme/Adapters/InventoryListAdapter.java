package in.silive.directme.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import in.silive.directme.R;

/**
 * Created by akriti on 13/5/16.
 */
public class InventoryListAdapter extends BaseAdapter {
    public static LayoutInflater inflater = null;
    String[] items;
    Integer[] item_img;
    Context c;
    Fragment fragment;

    public InventoryListAdapter(Context context, String[] str, Integer[] integers) {
        items = str;
        item_img = integers;
        c = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View view;
        view = inflater.inflate(R.layout.inventory_list_adapter, null);
        holder.item_name = (TextView) view.findViewById(R.id.adapter_text);
        holder.item_name.setText(items[position]);
        holder.item_image = (ImageView) view.findViewById(R.id.adapter_image);
        holder.item_image.setImageResource(item_img[position]);
        return view;
    }

    class Holder {
        TextView item_name;
        ImageView item_image;
        Button item_price;

    }
}
