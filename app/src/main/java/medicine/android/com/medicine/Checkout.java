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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Checkout extends AppCompatActivity {

    final static String[] values1 = new String[]{
            "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustomListViewAdapter customListViewAdapter;
        final ListView listView;

        ArrayList<HashMap<String, String>> titleList = new ArrayList<>();
        for (int i=0;i<10;i++){
            HashMap<String,String> data = new HashMap<>();
            data.put("title",values1[i]);

            titleList.add(data);
        }
        listView = (ListView) findViewById(R.id.listorders);

        customListViewAdapter = new CustomListViewAdapter(getApplicationContext(),titleList);

        listView.setAdapter(customListViewAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void clickedplaceorder()
    {
        Toast.makeText(getApplicationContext(),"Thank You for placing the order",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Checkout.this, MainActivity.class);
        startActivity(i);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.check_out, menu);
        return true;
    }

}
