package medicine.android.com.medicine;

import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class GetLocation extends AppCompatActivity {
    GoogleApiClient mGoogleApiClient;
    AddressResultReceiver mResultReceiver;
    private int location =1;

    private int PERMISSION_CODE_1 = 23;
    public static final String PACKAGE_NAME =
            "com.github.anurag145.gettinglastlocation";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";

    public static final String LOCATION_LATITUDE_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_LATITUDE_DATA_EXTRA";
    public static final String LOCATION_LONGITUDE_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_LONGITUDE_DATA_EXTRA";
    public static final int SUCCESS_RESULT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        if (Build.VERSION.SDK_INT >= 23) {

            if ( ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)) {

                }

                requestpermisions();


            }
        }

        mResultReceiver = new AddressResultReceiver(null);
        setupGoogleApiClient();

    }
    public void requestpermisions() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_CODE_1);
    }
    GoogleApiClient.ConnectionCallbacks mConnectionCallbacks =  new GoogleApiClient.ConnectionCallbacks()
    {    @Override    public void onConnected(Bundle bundle)
    {
        View view=findViewById(R.id.location);
        getLocation(view);
    }
        @Override
        public void onConnectionSuspended(int i) {} };

    GoogleApiClient.OnConnectionFailedListener  mOnConnectionFailedListener = new     GoogleApiClient.OnConnectionFailedListener() {
        @Override    public void onConnectionFailed(ConnectionResult connectionResult)
        { Toast.makeText(GetLocation.this, connectionResult.toString(), Toast.LENGTH_LONG).show();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",connectionResult.toString());
            setResult(location,returnIntent);
            finish();
        }
    };
    protected synchronized void setupGoogleApiClient()
    {    mGoogleApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(mConnectionCallbacks)
            .addOnConnectionFailedListener( mOnConnectionFailedListener)
            .addApi(LocationServices.API)
            .build();
        mGoogleApiClient.connect();
    }
    public void getLocation(View view) {

        try { Location lastLocation = LocationServices.FusedLocationApi.
                getLastLocation( mGoogleApiClient);
            if (lastLocation != null)
            {  Intent intent = new Intent(this, GeocodeAddressIntentService.class);
                intent.putExtra(RECEIVER, mResultReceiver);
                Log.d("shit",String.valueOf(
                        lastLocation.getLatitude()));

                Log.d("shitty",String.valueOf(lastLocation.getLongitude()));
                intent.putExtra(LOCATION_LATITUDE_DATA_EXTRA, lastLocation.getLatitude());
                intent.putExtra(LOCATION_LONGITUDE_DATA_EXTRA,lastLocation.getLongitude());

                startService(intent);

            } else {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","Location unavailable");
                setResult(location,returnIntent);
                finish();
            }
        }    catch (SecurityException e)
        {e.printStackTrace();
        }
    }

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }


        @Override
        protected void onReceiveResult(int resultCode, final Bundle resultData) {
            if (resultCode ==SUCCESS_RESULT) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",resultData.getString(RESULT_DATA_KEY));
                        setResult(location,returnIntent);
                        finish();

                    }
                });
            }
            else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",resultData.getString(RESULT_DATA_KEY));
                        setResult(location,returnIntent);
                        finish();

                    }
                });
            }
        }
    }
}




