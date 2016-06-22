package medicine.android.com.medicine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

/**
 * Created by Garg on 22-06-2016.
 */
public class CustomListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<HashMap<String,String>> books;
    private static LayoutInflater inflater = null;
    public CustomListViewAdapter(Context context,ArrayList<HashMap<String,String>> data){

        mContext = context;
        books = data;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return books.size();
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

        View view = convertView;

        view = inflater.inflate(R.layout.row,null);
        TextView title = (TextView) view.findViewById(R.id.title);

        HashMap<String,String> mBook;
        mBook = books.get(position);

            title.setText(mBook.get("title"));


        return view;
    }
}
