package com.example.app424;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class brodcast extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("inmmmmm9");
        int id =200;

        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        System.out.println("inmmmmm10");
        Intent repeating_Intent =new Intent(context, MainActivity.class);
        repeating_Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent=  PendingIntent.getActivity(context,0,repeating_Intent,PendingIntent.FLAG_CANCEL_CURRENT);
        System.out.println("inmmmmm11");
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"Notiii")
                .setSmallIcon(R.drawable.icon2)
                .setLargeIcon(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_action_mail),128,128,false))
                .setContentTitle("PASTICCINO")
                .setContentText("this is daly notii").setSound(alarmSound)
                .setAutoCancel(true).setWhen(when)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        System.out.println("inmmmmm12");
                 notificationManager.notify(id, builder.build());

                 id++;

                 System.out.println("inmmmmm3");

       /* NotificationManagerCompat   notificationManagerCompat= NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(200,builder.build());*/
    }
}
