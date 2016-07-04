package medicine.android.com.medicine;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
    Button b;
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
            }
        });




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
