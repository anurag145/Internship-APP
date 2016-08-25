package medicine.android.com.medicine;



import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ListDown extends AppCompatActivity {

    EditText e1,e2;
    String string;
    ArrayList<String> data = new ArrayList<>();
    Bundle checkout = new Bundle();
    Button b,a;
    SimpleDateFormat dateformat;
    private Calendar c ;
 String S="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_down);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        b = (Button) findViewById(R.id.button);
        a = (Button) findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                string = e1.getText().toString();
                data.add(string);
              if(S.equalsIgnoreCase(""))
                S=S+string;
                else
              S=S+","+string;
                e1.setText("");
                e2.setText(S);
                checkout.putString("listdown",S);
            }
        });

           a.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (!e2.getText().toString().equalsIgnoreCase(""))
                   {

                       IMAGE ob = new IMAGE();

                       dateformat = new SimpleDateFormat("MM.dd.yyyy", Locale.getDefault());
                       c= Calendar.getInstance(TimeZone.getDefault());

                       ob.date=dateformat.format(c.getTime()).toUpperCase();
                       dateformat= new SimpleDateFormat("HH:mm:ss a",Locale.getDefault());
                       ob.time=dateformat.format(c.getTime());
                       ob.list=e2.getText().toString();
                       ob.stored=1;
                       ob.save();

                       finish();
                   }
                   else
                       Toast.makeText(getApplicationContext(),"LIST EMPTY",Toast.LENGTH_LONG).show();
               }
           });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
