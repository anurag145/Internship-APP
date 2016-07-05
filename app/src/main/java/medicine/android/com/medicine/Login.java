package medicine.android.com.medicine;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;




public class Login extends AppCompatActivity  {
    CarouselView carouselView;
    int[] sampleImages = {R.mipmap.ic_launcher,R.mipmap.l,R.mipmap.m,R.mipmap.n};


    private Button signInButton;
private Button signInButton2;
    //Signing Options



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);



        carouselView.setImageListener(imageListener);

        //Initializing signinbutton
        signInButton = (Button) findViewById(R.id.sign_in_button_facebook);
        signInButton2=(Button)findViewById(R.id.sign_in_button_google);

        signInButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //Setting onclick listener to signing button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent( Login.this,FacebookLogin.class);
                startActivity(intent);
            }
        });


    }








    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }



    };


}
