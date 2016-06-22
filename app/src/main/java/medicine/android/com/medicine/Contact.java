package medicine.android.com.medicine;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String[] booktitles = new String[]{
                "Mayank","Garg","B.Tech","CSE","19","years","A","B","C","D","E","F","G","H"
        };

        final ListView listView;
        CustomListViewAdapter customListViewAdapter;

        ArrayList<HashMap<String, String>> titleList = new ArrayList<>();
        for (int i=1;i<10;i++){
            HashMap<String,String> data = new HashMap<>();
            data.put("title",booktitles[i]);

            titleList.add(data);
        }
        listView = (ListView) findViewById(R.id.list);

        customListViewAdapter = new CustomListViewAdapter(getApplicationContext(),titleList);

        listView.setAdapter(customListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int myPosition = position;
                String itemClickedID = listView.getItemAtPosition(myPosition).toString();
                Toast.makeText(getApplicationContext(),"ID Clicked : "+itemClickedID+1,Toast.LENGTH_SHORT).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
