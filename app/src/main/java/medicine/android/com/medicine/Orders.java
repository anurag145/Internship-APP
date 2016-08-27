package medicine.android.com.medicine;

import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Orders extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private static List<IMAGE2> list;
    static RecyclerView mRecyclerView;
    private static List<IMAGE> listy;
    static SimpleDateFormat dateformat ;
    protected static CustomAdapter mAdapter;
   static Calendar c;

    private static final int SPAN_COUNT = 2;
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER
    }
    protected static   LayoutManagerType mCurrentLayoutManagerType;
    protected static RecyclerView.LayoutManager mLayoutManager;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
     static String[] values1 = new String[]{
            "NO VALUE"
    };
    static String[] values3 = new String[]{
            "NO VALUE"
    };
    static String[] values4 = new String[]{
            "NO VALUE"
    };
    static String[] values5= new String[]{
            "NO VALUE"
    };
     static String[] values2 = new String[]{
            "NO VALUE"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

   dateformat = new SimpleDateFormat("MM.dd.yyyy", Locale.getDefault());
         c= Calendar.getInstance();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(Orders.this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            finish();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_orders, menu);
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

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        public  void valuefind()
        {


            list=get();
            if(!list.isEmpty())
            {
                values1= new String[list.size()];
                values3= new String[list.size()];
                values4= new String[list.size()];
                values5= new String[list.size()];
                for (int i = 0; i < list.size(); i++) {

                    values1[i]= list.get(i).date;
                    values3[i]= list.get(i).time;
                    values4[i]= "Delivery";
                    values5[i]= list.get(i).image;


                }
            }



        }
        public  void valuefind2()
        {
            String yo[]={"NO VALUE"};

            listy=get1();
            if(!listy.isEmpty())
            {

                 yo=new String[listy.size()];

                for (int i = 0; i < listy.size(); i++) {

                        yo[i] = listy.get(i).date;

                }
                }
            values2=yo;
            }






        static List<IMAGE> get1()
        {
            return  new Select().from(IMAGE.class).where("stored=?",2).execute();
        }

        static List<IMAGE2> get()
        {
            return  new Select().from(IMAGE2.class).where("stored=?",2).execute();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_orders, container, false);
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
                {
                    Context mcontext=this.getContext();
                    final ListView listView;
                    valuefind();

                    mRecyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView);
                    mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                    listView = (ListView) rootView.findViewById(R.id.listorders);
                    listView.setVisibility(View.GONE);
                    mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                    int scrollPosition = 0;
                    if (mRecyclerView.getLayoutManager() != null) {
                        scrollPosition = ((GridLayoutManager) mRecyclerView.getLayoutManager())
                                .findFirstCompletelyVisibleItemPosition();
                    }
                    mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                    mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.scrollToPosition(scrollPosition);
                    mAdapter = new CustomAdapter(values1,values3,values4,values5,getActivity());
                    mRecyclerView.setAdapter(mAdapter);

                    /*customListViewAdapter = new CustomListViewOrders(mcontext,titleList);

                    listView.setAdapter(customListViewAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            byte[] decodedString = Base64.decode(list.get(position).image, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            Dialog dialog;
                            dialog= new Dialog(getContext());
                            dialog.setContentView(R.layout.photo_dialog1);
                            dialog.setCancelable(true);


                            ImageView imageView2 =(ImageView)dialog.findViewById(R.id.imageView6);

                            imageView2.setImageBitmap(decodedByte);
                            dialog.show();
                        }
                    });
                    //--------------------------------------------
                */
                }

            else
                if(getArguments().getInt(ARG_SECTION_NUMBER)==2)
                {
                    Context mcontext=this.getContext();
                    final ListView listView;
                    mRecyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView);
                    mRecyclerView.setVisibility(View.GONE);
                    CustomListViewOrders customListViewAdapter;

                    ArrayList<HashMap<String, String>> titleList = new ArrayList<>();
                    valuefind2();

                    for (int i=0;i<values2.length;i++){

                        HashMap<String,String> data = new HashMap<>();
                        data.put("title",values2[i]);

                        titleList.add(data);
                    }
                    listView = (ListView) rootView.findViewById(R.id.listorders);

                    customListViewAdapter = new CustomListViewOrders(mcontext,titleList);

                    listView.setAdapter(customListViewAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Dialog dialog;
                            dialog= new Dialog(getContext());
                            dialog.setContentView(R.layout.photo_dialog);
                            dialog.setCancelable(true);

                            TextView imageView =(TextView) dialog.findViewById(R.id.textem);

                            TextView imageView2 =(TextView)dialog.findViewById(R.id.texter);
                            imageView.setVisibility(View.INVISIBLE);
                            imageView2.setVisibility(View.INVISIBLE);
                            TextView textView=(TextView)dialog.findViewById(R.id.texty);
                            textView.setText(listy.get(position).list);
                            textView.setVisibility(View.VISIBLE);
                            dialog.show();
                        }
                    });
                }


            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Image";
                case 1:
                    return "Text";
            }
            return null;
        }
    }
}
