package com.example.app424;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class signupTapFragment extends Fragment {

    EditText email,password,conpass,phon ;
    Button login ;
    float v=0;
    DBHelper DB;


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

    ViewGroup  root =(ViewGroup) inflater.inflate(R.layout.signup_tap_frag,container,false);

    email = root.findViewById(R.id.Email2);
    password=root.findViewById(R.id.password2);
    conpass=root.findViewById(R.id.conpassword);
    phon =root.findViewById(R.id.phone);
    login=root.findViewById(R.id.button_signup);

    DB=new DBHelper(getContext());

    email.setTranslationX(500);
    password.setTranslationX(500);
    conpass.setTranslationX(500);
    phon.setTranslationX(500);
    login.setTranslationX(500);



    email .setAlpha(v);
    password.setAlpha(v);
    conpass.setAlpha(v);
    phon.setAlpha(v);
    login.setAlpha(v);


    email.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
    phon.animate().translationX(0).alpha(1).setDuration(1300).setStartDelay(500).start();
    password.animate().translationX(0).alpha(1).setDuration(1800).setStartDelay(600).start();
    conpass.animate().translationX(0).alpha(1).setDuration(2000).setStartDelay(700).start();
    login.animate().translationX(0).alpha(1).setDuration(2300).setStartDelay(800).start();

    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            String emailtext=email.getText().toString();
            String passtext=password.getText().toString();
            String conpasstext=conpass.getText().toString();
            String phontext=phon.getText().toString();

            if(emailtext.equals("")) {
                email.setError("please enter Email");
            }else if(phontext.equals("")){
                phon.setError("please enter number phone");
            }
            else if(passtext.equals("")){
                password.setError("please enter password");
            }
            else{
                  if(passtext.equals(conpasstext)){
                      Boolean chechuseremail=DB.checkuseremail(emailtext);
                      if(chechuseremail==false) {
                          Boolean insert=DB.insertData(emailtext,passtext,phontext);
                          if(insert==true){
                              Toast.makeText(getContext(), "Register Successfully" ,Toast.LENGTH_LONG).show();
                              intent.putExtra("emailvalue",emailtext);
                              startActivity(intent);
                          }
                          else{
                              Toast.makeText(getContext(), "Register faild" ,Toast.LENGTH_LONG).show();
                          }
                      }
                      else{
                          Toast.makeText(getContext(), "User already exist!" ,Toast.LENGTH_LONG).show();
                      }

                      }
                  else{
                      Toast.makeText(getContext(), "password is not matching" ,Toast.LENGTH_LONG).show();
                  }
               }

        }
    });



    return root ;
}




}
