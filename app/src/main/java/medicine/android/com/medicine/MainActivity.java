package medicine.android.com.medicine;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;



import android.support.v7.widget.CardView;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.facebook.login.LoginManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




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
    private ImageLoader imageLoader;
    final private String google ="GOOGLE_LOGIN";
    final private String facebook ="FACEBOOK_LOGIN";
    private NetworkImageView profilePhoto;
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
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
       navigationView.findViewById(R.id.username);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView textView2=(TextView) headerView.findViewById(R.id.username);
        textView2.setText(User.name);
        textView2=(TextView) headerView.findViewById(R.id.useremail);
        textView2.setText(User.email);
        profilePhoto = (NetworkImageView) headerView.findViewById(R.id.profileImage);
        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();

        imageLoader.get(User.photo.toString(),
                ImageLoader.getImageListener(profilePhoto,
                        R.mipmap.ic_launcher,
                        R.mipmap.ic_launcher));
        profilePhoto.setImageUrl(User.photo.toString(), imageLoader);
        navigationView.setNavigationItemSelectedListener(this);
        if(LOC.send().equalsIgnoreCase("Your Location should appear here."))
        {
            intent = new Intent(this, GetLocation.class);
            startActivityForResult(intent, location);
        }else {
            textView.setText(LOC.send());

        }
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
            intent = new Intent(MainActivity.this, Login.class);
            if(auth.intentData.equalsIgnoreCase(facebook)) {
                auth.mAuth.signOut();
                LoginManager.getInstance().logOut();
            }

            if(auth.intentData.equalsIgnoreCase(google))
            {

                 intent.putExtra("login",google);


            }


            startActivity(intent);
            finish();
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

