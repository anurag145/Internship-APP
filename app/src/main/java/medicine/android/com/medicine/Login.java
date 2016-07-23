package medicine.android.com.medicine;



import android.content.Intent;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceAuthenticationProvider;
import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceUser;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.*;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;



import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;




public class Login extends AppCompatActivity  {
    CarouselView carouselView;
    private int PERMISSION_CODE_1 = 23;
    int[] sampleImages = {R.mipmap.ic_launcher,R.mipmap.l,R.mipmap.m,R.mipmap.n};

    private MobileServiceClient mClient;
    private Button signInButton;
private Button signInButton2;
    public static final String SHAREDPREFFILE = "temp";
    public static final String USERIDPREF = "uid";
    public static final String TOKENPREF = "tkn";
    //Signing Options



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= 23) {

            if (ActivityCompat.checkSelfPermission(Login.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {  requestpermisions();
            }
        }

        try {
            mClient = new MobileServiceClient(
                    "https://twokilo.azure-mobile.net/",
                    "bprbEdBfMdPozKNKnLcUQKPEgUKgof22",
                    this

            );

            if (loadUserTokenCache(mClient))
            {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }catch (Exception e)
        {
            Log.e("hello",e.toString());
        }
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);



        carouselView.setImageListener(imageListener);

        //Initializing signinbutton
        signInButton = (Button) findViewById(R.id.sign_in_button_facebook);
        signInButton2=(Button)findViewById(R.id.sign_in_button_google);

        signInButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListenableFuture<MobileServiceUser> mLogin = mClient.login(MobileServiceAuthenticationProvider.Google);

                Futures.addCallback(mLogin, new FutureCallback<MobileServiceUser>() {
                    @Override
                    public void onFailure(Throwable exc) {
                        Log.e("hello",exc.toString());
                    }
                    @Override
                    public void onSuccess(MobileServiceUser user) {
                        cacheUserToken(mClient.getCurrentUser());
                        Intent intent = new Intent(Login.this,MainActivity.class);

                        startActivity(intent);
                        finish();

                    }
                });
            }
        });


        //Setting onclick listener to signing button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListenableFuture<MobileServiceUser> mLogin = mClient.login(MobileServiceAuthenticationProvider.Facebook);

                Futures.addCallback(mLogin, new FutureCallback<MobileServiceUser>() {
                    @Override
                    public void onFailure(Throwable exc) {
                        Log.e("hello",exc.toString());
                    }
                    @Override
                    public void onSuccess(MobileServiceUser user) {
                        cacheUserToken(mClient.getCurrentUser());
                        Intent intent = new Intent(Login.this,MainActivity.class);

                        startActivity(intent);
                        finish();
                    }
                });
            }
        });


    }
    public void requestpermisions() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_CODE_1);

    }







    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }



    };
    private void cacheUserToken(MobileServiceUser user)
    {
        SharedPreferences prefs = getSharedPreferences(SHAREDPREFFILE, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putString(USERIDPREF, user.getUserId());
        editor.putString(TOKENPREF, user.getAuthenticationToken());
        editor.commit();
    }
    private boolean loadUserTokenCache(MobileServiceClient client)
    {
        SharedPreferences prefs = getSharedPreferences(SHAREDPREFFILE, Context.MODE_PRIVATE);
        String userId = prefs.getString(USERIDPREF, null);
        if (userId == null)
            return false;
        String token = prefs.getString(TOKENPREF, null);
        if (token == null)
            return false;

        MobileServiceUser user = new MobileServiceUser(userId);
        user.setAuthenticationToken(token);
        client.setCurrentUser(user);

        return true;
    }

}
