package medicine.android.com.medicine;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Anurag145 on 8/25/2016.
 */
@IgnoreExtraProperties
public class DatabaseEntry
{


       public  Map<String,Object> order;

        public DatabaseEntry() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public DatabaseEntry( Map<String ,Object> order) {

            this.order=order;

        }


}
