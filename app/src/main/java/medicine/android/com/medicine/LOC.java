package medicine.android.com.medicine;

/**
 * Created by Anurag145 on 8/20/2016.
 */
public class LOC
{
    private static LOC singleton = new LOC( );
  private static String loc="Your Location should appear here.";
    private static String loc1="Your Location should appear here.";

    private LOC(){ }




    protected static void get(String tmp ) {
        loc=tmp;
    }
    protected static String send()
    {
        return loc;
    }
    protected static void get1(String tmp ) {
        loc1=tmp;
    }
    protected static String send1()
    {
        return loc1;
    }
}

