package medicine.android.com.medicine;

import android.net.Uri;

/**
 * Created by Anurag145 on 8/25/2016.
 */
public class User {
    private static User singleton = new User( );
    public static User getSingleton() {
        return singleton;
    }
    static String uid;
    static String name;
    static String email;
    static Uri photo;


}
