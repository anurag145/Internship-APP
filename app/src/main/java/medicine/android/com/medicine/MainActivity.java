package medicine.android.com.medicine;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;


import android.support.v7.widget.CardView;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.List;


public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private int location =1;
private ImageView im;
private Intent intent;
    private CardView cardView;
    private CardView cardView2;
    private CardView cardView3;
    private CardView cardView4;
    private TextView tx;
    private TextView tx2;
    private Dialog dialog;
  private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cardView = (CardView)findViewById(R.id.cardview);
        cardView2 = (CardView) findViewById(R.id.cardview2);



       textView=(TextView)findViewById(R.id.textView7) ;


        cardView.setOnClickListener(this);

        cardView2.setOnClickListener(this) ;










        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(LOC.send().equalsIgnoreCase("Your Location should appear here."))
        {
            intent = new Intent(this, GetLocation.class);
            startActivityForResult(intent, location);
        }else
          textView.setText(LOC.send());
    }

    public void diagfrag()
    {
        dialog= new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.photo_dialog);
        dialog.setCancelable(true);
        dialog.show();
        tx =(TextView) dialog.findViewById(R.id.texter);
        tx2=(TextView)dialog.findViewById(R.id.textem);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,Upload.class);
                startActivity(intent);
                 dialog.dismiss();

            }
        });

        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,Gallery_Upload.class);
                startActivity(intent);
                dialog.dismiss();

            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id==R.id.cart)
        {
            intent= new Intent(this,Cart.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this,Orders.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent contact = new Intent(this, Contact.class);
            startActivity(contact);

        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(getApplicationContext(),"Can't Logout right now",Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.cardview :
                diagfrag();
                break;
            case R.id.cardview2 :
                intent = new Intent(MainActivity.this,ListDown.class);
                startActivity(intent);

                break;


            default: break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


            if(resultCode == location){
               LOC.get(data.getStringExtra("result"));


                textView.setText( LOC.send());
            }

        }
}

