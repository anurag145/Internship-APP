package medicine.android.com.medicine;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Cart extends AppCompatActivity {
private static List<IMAGE2> list;
    private static List<IMAGE> listy;
    static SimpleDateFormat dateformat ;
   static Calendar c;
    static RecyclerView mRecyclerView;
 static    ArrayList<HashMap<String, String>> titleList;
   static CustomListViewOrders customListViewAdapter;
    final Activity act=this;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
     static String[] values1 = new String[]{
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
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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
            String yo[]={"NO VALUE"};

            list=get();
            if(!list.isEmpty())
            {
                yo= new String[list.size()];
                for (int i = 0; i < list.size(); i++) {

                    yo[i]= list.get(i).date;


                }
            }
         values1=yo;


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
            return  new Select().from(IMAGE.class).where("stored=?",1).execute();
        }

        static List<IMAGE2> get()
        {
            return  new Select().from(IMAGE2.class).where("stored=?",1).execute();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_orders, container, false);
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
                {
                    final Context mcontext=this.getContext();
                   final  ListView listView;
                    mRecyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView);
                    mRecyclerView.setVisibility(View.GONE);

                     titleList = new ArrayList<>();
                    valuefind();

                    for (int i=0;i<values1.length;i++){
                        HashMap<String,String> data = new HashMap<>();

                        data.put("title",values1[i]);

                        titleList.add(data);
                    }
                    listView = (ListView) rootView.findViewById(R.id.listorders);

                    customListViewAdapter = new CustomListViewOrders(mcontext,titleList);

                    listView.setAdapter(customListViewAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                          final  Dialog dialog;
                            dialog= new Dialog(getContext());
                            dialog.setContentView(R.layout.photo_dialog2);
                            dialog.setCancelable(true);
                            Button button=(Button)dialog.findViewById(R.id.button3) ;
                            Button button2=(Button)dialog.findViewById(R.id.button4) ;
                            dialog.show();
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                               new Delete().from(IMAGE2.class).where("image=?",list.get(position).image).execute();
                                  valuefind();
                                    titleList = new ArrayList<>();
                                    for (int i=0;i<values1.length;i++){
                                        HashMap<String,String> data = new HashMap<>();

                                        data.put("title",values1[i]);

                                        titleList.add(data);
                                    }
                                    customListViewAdapter = new CustomListViewOrders(mcontext,titleList);
                                    listView.setAdapter(customListViewAdapter);
                                    dialog.dismiss();
                                }

                            });

                            button2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Map<String,Object> order= new HashMap<String, Object>();
                                    order.put("Date",list.get(position).date);
                                    order.put("Image",list.get(position).image);
                                    order.put("Time",list.get(position).time);
                                    order.put("value",1);
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("me");

                                    DatabaseEntry ob = new DatabaseEntry(order);
                                    myRef.child("Users").child(User.getSingleton().uid).child(User.getSingleton().name).child(list.get(position).time).setValue(ob);
                                    new Update(IMAGE2.class).set("stored=?",2).where("image=?",list.get(position).image).execute();
                                    valuefind();
                                    titleList = new ArrayList<>();
                                    for (int i=0;i<values1.length;i++){
                                        HashMap<String,String> data = new HashMap<>();

                                        data.put("title",values1[i]);

                                        titleList.add(data);
                                    }
                                    customListViewAdapter = new CustomListViewOrders(mcontext,titleList);
                                    listView.setAdapter(customListViewAdapter);
                                    dialog.dismiss();
                                }

                            });



                        }
                    });
                    //--------------------------------------------
                }
            else
                if(getArguments().getInt(ARG_SECTION_NUMBER)==2)
                {
                    final Context mcontext=this.getContext();
                    final ListView listView;

                    mRecyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView);
                    mRecyclerView.setVisibility(View.GONE);
                     titleList = new ArrayList<>();
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
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                           final Dialog dialog;
                            dialog= new Dialog(getContext());
                            dialog.setContentView(R.layout.photo_dialog2);
                            dialog.setCancelable(true);
                            Button button=(Button)dialog.findViewById(R.id.button3) ;
                            Button button2=(Button)dialog.findViewById(R.id.button4) ;
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    new Delete().from(IMAGE.class).where("list=?",listy.get(position).list).execute();
                                    valuefind();
                                    titleList = new ArrayList<>();
                                    for (int i=0;i<values1.length;i++){
                                        HashMap<String,String> data = new HashMap<>();

                                        data.put("title",values1[i]);

                                        titleList.add(data);
                                    }
                                    customListViewAdapter = new CustomListViewOrders(mcontext,titleList);
                                    listView.setAdapter(customListViewAdapter);
                                    dialog.dismiss();
                                }

                            });

                            button2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Map<String,Object> order= new HashMap<String, Object>();
                                    order.put("Date",listy.get(position).date);
                                    order.put("Image",listy.get(position).list);
                                    order.put("Time",listy.get(position).time);
                                    order.put("value",2);
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("me");

                                    DatabaseEntry ob = new DatabaseEntry(order);
                                    myRef.child("Users").child(User.getSingleton().uid).child(User.getSingleton().name).child(listy.get(position).time).setValue(ob);
                                    new Update(IMAGE.class).set("stored=?",2).where("list=?",listy.get(position).list).execute();
                                    valuefind2();
                                    titleList = new ArrayList<>();
                                    for (int i=0;i<values2.length;i++){
                                        HashMap<String,String> data = new HashMap<>();

                                        data.put("title",values1[i]);

                                        titleList.add(data);
                                    }
                                    customListViewAdapter = new CustomListViewOrders(mcontext,titleList);
                                        listView.setAdapter(customListViewAdapter);
                                    dialog.dismiss();
                                }

                            });
                            dialog.show();


                        }
                    });
                }


            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
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
