package medicine.android.com.medicine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;

public class ScrollingActivity extends AppCompatActivity {
private TextView textView;
private  TextView textView2;
private TextView textView3;
   static String time;static String date;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView6);
        imageView=(ImageView) findViewById(R.id.imageView);
        date=getIntent().getStringExtra("Date");
        textView.setText(date);
        time=getIntent().getStringExtra("Time");
        textView2.setText(time);
        textView3.setText(getIntent().getStringExtra("Delivery"));
        byte[] decodedString = Base64.decode(get1().get(0).image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);

    }
    static List<IMAGE2> get1()
    {
        return  new Select().from(IMAGE2.class).where("date=?",date).where("time=?",time).execute();
    }
}
