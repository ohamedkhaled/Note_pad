package com.example.app424;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class MainActivity extends AppCompatActivity /*implements NotesAdapter.Selectnote*/{

    NotesAdapter notesAdapter;
    LinearLayout linearLayout;
    TextView textViewuser,textViewnote ;
    FloatingActionButton note,Fav;
    String sthome, sttitle, stdisc,urllink,time  ;
    static int count;
    Boolean flagcount,chechfav;
    Bitmap image;
    List<NoteModel> notesList,notesFavList;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
      // LoginActivity loginActivity = new LoginActivity();


        linearLayout=findViewById(R.id.Fram);
       textViewuser=findViewById(R.id.textViewmain);
       textViewnote=findViewById(R.id.textviewnote);
       note =findViewById(R.id.floatingActionButton);
       Fav=findViewById(R.id.floatingActionButtonFAV);

       sthome =getIntent().getExtras().getString("emailvalue");
       textViewuser.setText("Welcom"+ sthome);

        notesList = new ArrayList<>();
        notesList.add(new NoteModel("firstNote","FISET NOTE"));

        notesFavList=new ArrayList<>();
        notesFavList.add(new NoteModel("firstFAVNote","FISET  FAV NOTE"));


       FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Fram,new recyclerViewFragment(notesList));
        fragmentTransaction.commit();

        Fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Fram,new recyclerViewFavFragment(notesList));
                fragmentTransaction.commit();

*/
                NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this,"Notiii")
                        .setSmallIcon(R.drawable.icon2)
                        .setContentTitle("PASTICCINO")
                        .setContentText("this is daly notii");
                NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(0,builder.build());

            }
        });
        Fav.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Fram,new recyclerViewFavFragment(notesFavList));
                fragmentTransaction.commit();

                Toast.makeText(getBaseContext(), "Favorites Notes" ,Toast.LENGTH_LONG).show();

                return false;
            }
        });

      //notifcation


        /*Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,2);
        calendar.set(Calendar.MINUTE,47);
        calendar.set(Calendar.SECOND,00);

        if(Calendar.getInstance().after(calendar)){

                calendar.add(Calendar.DAY_OF_MONTH,1);

            System.out.println("immmmmmm2");
        }

        System.out.println("immmmmmm5");

        Intent intent =new Intent(MainActivity.this,brodcast.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        System.out.println("immmmmmm6");


        AlarmManager alarmManger  =(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManger.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_HOUR,pendingIntent);
        System.out.println("immmmmmm7");
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            alarmManger.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            System.out.println("immmmmmm8");
        }

*/



        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Notes.class);
                intent.putExtra("flagcount",flagcount);
                startActivityForResult(intent,2);

            }
        });



    }

    void change(){
        count++;
        textViewnote.setText("Note("+count+")");
    }



  private  void NotificationChannel(){

    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
        CharSequence name ="PASTICCINO";
        String description="PASTICCINO'S CHANNEL";

        System.out.println("immmmmmm");

        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel=new NotificationChannel("Notiii",name,importance);

        channel.setDescription(description);
      }



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2 && resultCode ==RESULT_OK){

         sttitle =data.getStringExtra("title");
         stdisc =data.getStringExtra("disc");
         urllink=data.getStringExtra("urllink");
         image=data.getParcelableExtra("image");
         time=data.getStringExtra("time");
         chechfav=data.getBooleanExtra("fav",false);




            boolean flag =true;

            if(flag){
            change();
          }
            if(chechfav){
             notesFavList.add(new NoteModel(sttitle,stdisc));
             notesList.add(new NoteModel(sttitle, stdisc));
            }else {

                notesList.add(new NoteModel(sttitle, stdisc));
            }

            recyclerViewFragment recyclerViewFragment =new recyclerViewFragment(notesList);


            if(recyclerViewFragment.getNotesList()!=null){
                System.out.println(recyclerViewFragment.getNotesList().get(0).getTit());
                System.out.println(recyclerViewFragment.getNotesList().get(1).getTit());

            }



            Bundle b =new Bundle();
            b.putString("ti",sttitle);
            b.putString("dis", stdisc);



            FragmentManager fragmentManager=getSupportFragmentManager();

            recyclerViewFragment.setArguments(b);

            getSupportFragmentManager().beginTransaction().replace(R.id.Fram,recyclerViewFragment).commit();








        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.menu,menu);
       MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView=(SearchView) menuItem.getActionView();
       searchView.setMaxWidth(Integer.MAX_VALUE);

       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {

               notesAdapter.getFilter().filter(newText);
               return true;
           }
       });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id =item.getItemId();

       if(id==R.id.search_view){

           return true;
       }



        return true;
    }






}