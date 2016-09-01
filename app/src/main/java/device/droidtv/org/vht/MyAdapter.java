package device.droidtv.org.vht;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amit.goyal on 8/29/2016.
 */
public class MyAdapter extends ArrayAdapter<Item> {
    private ArrayList<Item> mArrayList;
    private Context mContext = null;
    public MyAdapter(Context context, ArrayList<Item> arrayList){
        super(context, R.layout.row_content, arrayList);
        mContext = context;
        mArrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_content, parent, false);

        TextView title = (TextView)rowView.findViewById(R.id.title);
        TextView description = (TextView)rowView.findViewById(R.id.description);
        title.setText(mArrayList.get(position).getTitle());
        description.setText(mArrayList.get(position).getDescription());
        return rowView;
    }

    public void setItemList(ArrayList<Item> list) {
        this.mArrayList = list;
    }
}
