package medicine.android.com.medicine;


import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


/**
 * Created by Anurag on 6/24/2016.
 */
public class App extends MultiDexApplication{
    @Override
    public void onCreate() {
        super.onCreate();




    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
