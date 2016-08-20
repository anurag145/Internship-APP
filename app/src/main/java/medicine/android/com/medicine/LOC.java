package medicine.android.com.medicine;

/**
 * Created by Anurag145 on 8/20/2016.
 */
public class LOC
{
    private static LOC singleton = new LOC( );
  private static String loc="Your Location should appear here.";

    private LOC(){ }


    public static LOC getInstance( ) {
        return singleton;
    }

    protected static void get(String tmp ) {
        loc=tmp;
    }
    protected static String send()
    {
        return loc;
    }
}
