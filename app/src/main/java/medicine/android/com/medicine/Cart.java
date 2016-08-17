package medicine.android.com.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart extends AppCompatActivity {

    final static String[] values1 = new String[]{
            "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i = new Intent(Cart.this,MainActivity.class);
                startActivity(i);
            }
        });

        CustomListViewAdapter customListViewAdapter;
        final ListView listView;

        ArrayList<HashMap<String, String>> titleList = new ArrayList<>();
        for (int i=0;i<10;i++){
            HashMap<String,String> data = new HashMap<>();
            data.put("title",values1[i]);

            titleList.add(data);
        }
        listView = (ListView) findViewById(R.id.listy);

        customListViewAdapter = new CustomListViewAdapter(getApplicationContext(),titleList);

        listView.setAdapter(customListViewAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void clickedcheckout()
    {
        Intent i = new Intent(Cart.this, Checkout.class);
        startActivity(i);
    }



}
