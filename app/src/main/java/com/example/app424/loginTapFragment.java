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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import androidx.fragment.app.Fragment;

public class loginTapFragment  extends Fragment {

    EditText email,password ;
    TextView forget ;
    Button login ;
    float v=0;
    DBHelper DB;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup  root =(ViewGroup) inflater.inflate(R.layout.login_tab_frag,container,false);
         email = root.findViewById(R.id.Email);
         password=root.findViewById(R.id.password);
         forget=root.findViewById(R.id.text1);
         login =root.findViewById(R.id.button_login);

        DB=new DBHelper(getContext());

         email.setTranslationX(0);
         password.setTranslationX(0);
         forget.setTranslationX(0);
         login.setTranslationX(0);

         email.setAlpha(v);
         password.setAlpha(v);
         forget.setAlpha(v);
         login.setAlpha(v);


         email.animate().translationY(0).alpha(1).setDuration(3000).setStartDelay(3000).start();
         password.animate().translationY(0).alpha(1).setDuration(3500).setStartDelay(3500).start();
         forget.animate().translationY(0).alpha(1).setDuration(4000).setStartDelay(4000).start();
         login.animate().translationY(0).alpha(1).setDuration(5500).setStartDelay(4500).start();


login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

      String  emailtext=email.getText().toString();
      String passtext=password.getText().toString();

        if(emailtext.equals("")||passtext.equals(""))
            Toast.makeText(getContext(), "please enter all the fields" ,Toast.LENGTH_LONG).show();
        else{
            Boolean chechuserpass=DB.checkuserpassword(emailtext,passtext);
            if(chechuserpass==true){
                Toast.makeText(getContext(), "Sign in is Successfully " ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(inflater.getContext() , MainActivity.class);
                intent.putExtra("emailvalue",emailtext);
                startActivity(intent);
            }else{
                Toast.makeText(getContext(), "invalid password or email " ,Toast.LENGTH_LONG).show();
            }





        }

    }
});



        return root ;
    }



}
