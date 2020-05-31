package com.example.bisonapp70;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiverP extends BroadcastReceiver {
    String titulo = "Tienes un proyecto programado para ahora mismo ";
    String desc="Presiona para ver más información";
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent resultIntent1 = new Intent(context, Mostrar.class);
        resultIntent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent1 = PendingIntent.getActivity(context,0,resultIntent1,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notificar")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(titulo)
                .setContentText(desc)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(intent1);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200,builder.build());
    }
}
