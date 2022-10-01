package com.example.jobportal;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;



import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Profile extends AppCompatActivity {

    public static TextView tv_personal,tv_education;
    private boolean onbackpressed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        tv_personal=findViewById(R.id.TV_personal);
        tv_education=findViewById(R.id.TV_education);

        tv_personal.setBackgroundColor(Color.rgb(255,87,34));
        tv_personal.setTextColor(Color.rgb(255, 255, 255));
        loadFragmentProfile(new Profile_personal());

    }


    @Override
    public void onBackPressed() {
       if(!onbackpressed){

            View v=findViewById(android.R.id.content);
            Snackbar.make(v,"Press back again to exit "+ Html.fromHtml("&#9995;"),Snackbar.LENGTH_SHORT).show();

            onbackpressed=true;
        }
        else{
            super.onBackPressed();
        }
        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                onbackpressed=false;
            }
        }.start();
    }


    private void loadFragmentProfile(Fragment fragment) {

        String email=getIntent().getStringExtra("email");
        String password=getIntent().getStringExtra("password");
        //Sending email and password to Fragments
        Bundle bundle=new Bundle();
        bundle.putString("email",email);
        bundle.putString("password",password);
        fragment.setArguments(bundle);
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.FL_profile,fragment);
        ft.commit();

    }


}
