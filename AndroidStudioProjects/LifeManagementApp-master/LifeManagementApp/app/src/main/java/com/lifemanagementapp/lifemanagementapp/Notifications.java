package com.lifemanagementapp.lifemanagementapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by bcopsahl on 2/27/17.
 */
public class Notifications extends AppCompatActivity {

   public void addNotification() {

       NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
               .setSmallIcon(R.mipmap.ic_launcher)
               .setContentTitle("My notification")
               .setContentText("Hello World!");

       Intent intent = new Intent(this, Homescreen.class);

       PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

       notificationBuilder.setContentIntent( pendingIntent );

       NotificationManager notificationManager = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );

       notificationManager.notify( 0, notificationBuilder.build( ) );

   }

}
