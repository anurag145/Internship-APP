package medicine.android.com.medicine;

import android.graphics.Bitmap;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Anurag145 on 8/17/2016.
 */
@Table(name ="IMAGE")
public class IMAGE extends Model {
    @Column(name="date")
    public  String date;

    @Column(name ="list")
    public String list;



}
