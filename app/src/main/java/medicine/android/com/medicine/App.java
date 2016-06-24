package medicine.android.com.medicine;

import android.app.Application;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

/**
 * Created by Anurag on 6/24/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
    }
}
