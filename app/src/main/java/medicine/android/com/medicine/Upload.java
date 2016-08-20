package medicine.android.com.medicine;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Upload extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView2;
    private ImageView imageView;
    SimpleDateFormat dateformat;
  View view;
    Bitmap photo;

    private Calendar c ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        imageView=(ImageView)findViewById(R.id.imageView3);
        imageView2=(ImageView)findViewById(R.id.imageView4);
        imageView.setEnabled(false);

//check if user has camera
        if(hasCamera())
           launchCamera(findViewById(R.id.camera));
        else {
            Toast.makeText(getApplicationContext(),"NO CAMERA",Toast.LENGTH_LONG).show();
            finish();
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMAGE2 ob= new IMAGE2();
                ob.date=dateformat.format(c.getTime()).toUpperCase();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                ob.image=Base64.encodeToString(byteArray, Base64.DEFAULT);
                ob.stored=1;
                ob.save();
                finish();
            }
        });

    }

    private boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //launch camera
    public void launchCamera(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_IMAGE_CAPTURE&&resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            photo=(Bitmap)bundle.get("data");
            dateformat = new SimpleDateFormat("MM.dd.yyyy", Locale.getDefault());
            c= Calendar.getInstance();
            imageView.setEnabled(true);
            imageView2.setImageBitmap(photo);


        }


    }
}