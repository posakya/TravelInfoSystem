package com.marr.busreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    //Animation Fade_in,Fade_out;
    private Button btn_travel,btn_contactus,btn_aboutus,btn_share;
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getText(R.string.Home));
       // viewFlipper=(ViewFlipper)findViewById(R.id.ViewFlipper);
       // viewFlipper.setAnimation(Fade_in);
       // viewFlipper.setAnimation(Fade_out);
       // viewFlipper.setAutoStart(true);
       // viewFlipper.startFlipping();
//        viewFlipper.setFlipInterval(1000);
        imageView=(ImageView) findViewById(R.id.imageView);
        btn_travel=(Button) findViewById(R.id.btn_travel);
        btn_aboutus=(Button) findViewById(R.id.btn_about_us);
        btn_contactus=(Button) findViewById(R.id.btn_contact_us);
        btn_share=(Button) findViewById(R.id.btn_Share);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
        imageView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
                                           @Override
                                           public void onAnimationStart(Animation animation) {

                                           }

                                           @Override
                                           public void onAnimationEnd(Animation animation) {

                                           }

                                           @Override
                                           public void onAnimationRepeat(Animation animation) {

                                           }
                                       }

        );
        btn_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Bus.class));
            }
        });
    }

}
