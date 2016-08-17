package medicine.android.com.medicine;

import android.graphics.Bitmap;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Anurag145 on 8/17/2016.
 */
@Table(name ="IMAGE2")
public class IMAGE2 extends Model {
    @Column(name="date")
    public  String date;



    @Column(name="image")
    public String image;

}
