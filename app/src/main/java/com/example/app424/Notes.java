package com.example.app424;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Notes extends AppCompatActivity {

    EditText title, descr;
    Button save;
    String tit;
    String dis ;
    String urllink;
    CheckBox checkBox ,fav;
    Context context;
    Bitmap image;
    boolean chechfav;
    DBHelper MyDB;



    //ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        title = findViewById(R.id.titletext);
        descr = findViewById(R.id.descriptiontext);
        save = findViewById(R.id.button_save);
        checkBox=findViewById(R.id.checkbox);
        fav=findViewById(R.id.fav);



       tit=title.getText().toString();
       dis=descr.getText().toString();

       urllink=getIntent().getExtras().getString("urllinkchek");
       image=getIntent().getExtras().getParcelable("imageview");

       MyDB=new DBHelper(this );


       checkBox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               boolean isCkecked=((CheckBox)v).isChecked();
               update(isCkecked);
           }
       });

       fav.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               boolean isCkecked=((CheckBox)v).isChecked();

               if(isCkecked){
                   chechfav=true;

               }
           }
       });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().equals("")&&descr.getText().toString().equals("")) {
                    Toast.makeText(Notes.this, "please enter the title and description", Toast.LENGTH_LONG).show();

                }else{
                    Boolean result = MyDB.addNots(title.getText().toString(),descr.getText().toString());
                    if(result==true){
                        Toast.makeText(Notes.this , "Successfully seved" ,Toast.LENGTH_LONG).show();
                        boolean  flagcount=true;
                        Intent intent = new Intent(Notes.this, MainActivity.class);
                        Locale locale = new Locale("fr", "FR");
                        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
                        String date = dateFormat.format(new Date());
                        intent.putExtra("time",date);
                        intent.putExtra("flagcountReturn", flagcount);
                        intent.putExtra("title",title.getText().toString());
                        intent.putExtra("disc",descr.getText().toString());
                        intent.putExtra("urllink",urllink);
                        intent.putExtra("image",image);
                        intent.putExtra("fav",chechfav);
                        setResult(RESULT_OK,intent);
                        finish();

                    }else
                        Toast.makeText(Notes.this , "do not seved !!" ,Toast.LENGTH_LONG).show();


                }
            }

        });


    }

    private void update( boolean isCkecked) {
        if(isCkecked){
            context=Notes.this;
            replaceFragment(new noteCheckFragment());

        }else{
            replaceFragment2(new fragmentnull());
        }
    }

    private void replaceFragment2(fragmentnull fragmentnul) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewpager2,fragmentnul);
        fragmentTransaction.commit();

    }

    private void replaceFragment( noteCheckFragment noteCheckFragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewpager2,noteCheckFragment);
        fragmentTransaction.commit();

    }


}