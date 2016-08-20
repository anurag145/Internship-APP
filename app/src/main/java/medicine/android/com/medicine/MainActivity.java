package medicine.android.com.medicine;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;


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
    ImageButton upload,list,cart;
    private int location =1;
private ImageView im;
private Intent intent;
    private ImageView imageView;
    private ImageView imageView1;
    private Dialog dialog;
  private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        upload = (ImageButton)findViewById(R.id.imageButton);
        list = (ImageButton)findViewById(R.id.imageButton2);

             im =(ImageView)findViewById(R.id.imageView2);

       textView=(TextView)findViewById(R.id.textView7) ;


        upload.setOnClickListener(this);

        list.setOnClickListener(this) ;










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
        imageView =(ImageView)dialog.findViewById(R.id.imageView5);
        imageView1=(ImageView)dialog.findViewById(R.id.imageView6);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,Upload.class);
                startActivity(intent);


            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,Gallery_Upload.class);
                startActivity(intent);

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
            case R.id.imageButton :
                diagfrag();
                break;
            case R.id.imageButton2 :
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
                Toast.makeText(getApplicationContext(),LOC.send(),Toast.LENGTH_LONG).show();
                textView.setText( LOC.send());
            }

        }
}

