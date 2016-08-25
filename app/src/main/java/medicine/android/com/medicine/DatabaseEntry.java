package medicine.android.com.medicine;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Anurag145 on 8/25/2016.
 */
@IgnoreExtraProperties
public class DatabaseEntry
{

        public String username;
        public String email;
        public Uri Url;

        public DatabaseEntry() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public DatabaseEntry(String username, String email,Uri Url) {
            this.username = username;
            this.email = email;
            this.Url=Url;
        }


}
