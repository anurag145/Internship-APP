package medicine.android.com.medicine;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ListDown extends AppCompatActivity {

    EditText e1,e2;
    String string;
    ArrayList<String> data = new ArrayList<>();
    Bundle checkout = new Bundle();
    Button b,a,c;
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
        c = (Button) findViewById(R.id.button3);
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
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(ListDown.this,Cart.class);
                change.putExtra("listdown",checkout);
                startActivity(change);
            }
        });




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
