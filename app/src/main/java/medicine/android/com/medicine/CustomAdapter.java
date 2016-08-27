

package medicine.android.com.medicine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;


/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private String[] mDataSet;
    private String[] mDataSet1;
    private String[] mDataSet2;
    private String[] mDataSet3;
    private static HashMap<String,String> hashMap;
    private static Activity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;
        public final TextView textView1;
        public final TextView textView2;
        public final ImageView View3;
        public final ImageButton button;

        public ViewHolder(View v) {
            super(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                                   }
            });
            textView = (TextView) v.findViewById(R.id.date);
            textView1 = (TextView) v.findViewById(R.id.time);
            textView2 = (TextView) v.findViewById(R.id.delivery);

            View3 = (ImageView) v.findViewById(R.id.photomy);
            button=(ImageButton)v.findViewById(R.id.button5);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(activity,ScrollingActivity.class);
                    intent.putExtra("Date",textView.getText().toString());
                    intent.putExtra("Time",textView1.getText().toString());
                    intent.putExtra("Delivery",textView2.getText().toString());

                    activity.startActivity(intent);

                }
            });
        }



    }


    public CustomAdapter(String[] dataSet,String[] dataSet1,String[] dataSet2,String[] dataSet3,Activity activity) {
        mDataSet = dataSet;
        mDataSet1 = dataSet1;
        mDataSet2 = dataSet2;
        mDataSet3 = dataSet3;
        this.activity=activity;
        hashMap=new HashMap<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.textView.setText(mDataSet[position]);
        viewHolder.textView1.setText(mDataSet1[position]);
        viewHolder.textView2.setText(mDataSet2[position]);

        hashMap.put(mDataSet[position]+mDataSet1,mDataSet3[position]);
        if(!mDataSet3[position].equalsIgnoreCase("NO VALUE"))
        {
            byte[] decodedString = Base64.decode(mDataSet3[position], Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            viewHolder.View3.setImageBitmap(decodedByte);
        }



    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
