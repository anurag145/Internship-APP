package medicine.android.com.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;




/**
 * Created by anura_000 on 7/6/2016.
 */

public class GoogleLogin extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {


    private SignInButton signInButton;


    private GoogleSignInOptions gso;


    private GoogleApiClient mGoogleApiClient;


    private int RC_SIGN_IN = 100;


    private TextView textViewName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);


        textViewName = (TextView) findViewById(R.id.textViewName);




        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        signInButton.setOnClickListener(this);
    }



    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);


        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            handleSignInResult(result);
        }
    }



    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {

            GoogleSignInAccount acct = result.getSignInAccount();


            textViewName.setText(acct.getDisplayName());





        } else {

            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {

            signIn();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}

