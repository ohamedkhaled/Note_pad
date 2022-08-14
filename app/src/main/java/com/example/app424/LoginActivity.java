package com.example.app424;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.time.Instant;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class LoginActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {


    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fb, google, tw;
    float v = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);
        fb = findViewById(R.id.fab_fb);
        google = findViewById(R.id.fab_google);
        tw = findViewById(R.id.fab_tw);


        tabLayout.addTab(tabLayout.newTab().setText("SignUp"));
        tabLayout.addTab(tabLayout.newTab().setText("Login"));

        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);


        LoginAdapter adapter =new LoginAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));





        tabLayout.setTranslationX(0);
        fb.setTranslationY(300);
        fb.setTranslationX(30);
        google.setTranslationY(300);
        tw.setTranslationY(300);
        tw.setTranslationX(-30);


        tabLayout.setAlpha(v);
        fb.setAlpha(v);
        google.setAlpha(v);
        tw.setAlpha(v);


        tabLayout.animate().translationY(0).alpha(1).setDuration(3000).setStartDelay(1000).start();
        fb.animate().translationY(0).alpha(1).setDuration(3500).setStartDelay(1500).start();
        google.animate().translationY(0).alpha(1).setDuration(4000).setStartDelay(2000).start();
        tw.animate().translationY(0).alpha(1).setDuration(4500).setStartDelay(2500).start();

      google.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Uri uri=Uri.parse("http://www.google.com");
              Intent intent =new Intent(Intent.ACTION_VIEW,uri);
              intent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_SINGLE_TOP);

              startActivity(intent);
          }
      });


        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.twitter.com");
                Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.facebook.com");
                Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);
            }
        });

}
    @Override
    protected void onStart() {



        super.onStart();






    }

    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}




