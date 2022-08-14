package com.example.app424;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AndroidException;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

public class noteCheckFragment extends Fragment {

    private int StORAGE_PERMISSION_CODE=1;

    EditText url;
    Button remove,load;
    ImageView imageView ;
    float v=0;
    String urllink;


  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup  root =(ViewGroup) inflater.inflate(R.layout.imageload,container,false);




        url=root.findViewById(R.id.url);
        remove=root.findViewById(R.id.button_remove);
        load=root.findViewById(R.id.button_load);
        imageView=root.findViewById(R.id.image);

        url.setTranslationX(0);
        remove.setTranslationX(0);
        load.setTranslationX(0);
       imageView.setTranslationX(0);



        url.setAlpha(v);
        remove.setAlpha(v);
        load.setAlpha(v);
        imageView.setAlpha(v);


        url.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        remove.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(900).start();
        load.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        imageView.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1200).start();


        remove.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                url.setText("");
                imageView.setImageBitmap(null);
            }
        });

        load.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                 urllink =url.getText().toString();
                  new NoteModel().setURL(urllink);

                if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION)
                       == PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED){
                    //ACTIONPERMISSION
                    Toast.makeText(getContext() ,"<<<YOU HAVE AREADY PERMISSION>>>",Toast.LENGTH_LONG).show();



                if(urllink.isEmpty()){
                    Toast.makeText(getContext() ,"please enter URL>>>",Toast.LENGTH_LONG).show();

                }else if(!(Patterns.WEB_URL.matcher((url.getText().toString())).matches())){
                    url.setError("error");

                }else {
                    LoadImage loadImage = new LoadImage(imageView);
                    loadImage.execute(urllink);

                    Intent intent= new Intent();
                    intent.putExtra("urllinkchek",urllink);
                    if (loadImage.imageView.isOpaque()) {
                          intent.putExtra("imageview", (Parcelable) loadImage);
                    }

                  

                }
            }else {
                    requestStorgepermission();
                    System.out.println("innnnnnnnnnnnnnn");



                }


            }


        });
        return root ;
    }

    private void requestStorgepermission() {

      if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(),Manifest.permission.ACCESS_FINE_LOCATION)) {



          new AlertDialog.Builder(getContext()).setTitle("Permission needed")
                  .setMessage("this Permission needed because  locion detected  successfully")
                  .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          ActivityCompat.requestPermissions((Activity) getContext(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},StORAGE_PERMISSION_CODE);
                      }
                  })
                  .setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();

                      }
                  }).create().show();



      }else{
          ActivityCompat.requestPermissions((Activity) getContext(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},StORAGE_PERMISSION_CODE);
      }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==StORAGE_PERMISSION_CODE){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext() ,"PERMISSION GRANTED>>>",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getContext() ,"PERMISSION DENIED>>>",Toast.LENGTH_LONG).show();
            }
        }

    }

    private class LoadImage extends AsyncTask<String,Void, Bitmap> {

        ImageView imageView ;
        public LoadImage(ImageView imageView) {
            this.imageView=imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            String urllink=strings[0];
            Bitmap bitmap=null;

            try {
                InputStream inputStream =new java.net.URL(urllink).openStream();
                bitmap= BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Eroor");


            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }



}
