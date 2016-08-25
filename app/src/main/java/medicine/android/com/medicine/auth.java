package medicine.android.com.medicine;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Anurag145 on 8/25/2016.
 */
public class auth {
    private static auth singleton = new auth( );
    static FirebaseAuth mAuth=FirebaseAuth.getInstance();
     static int flag=0;
    static String intentData="";

    static GoogleApiClient mGoogleApiClient;
}
