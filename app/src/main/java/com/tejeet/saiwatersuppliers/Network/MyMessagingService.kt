package com.tejeet.saiwatersuppliers.Network

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tejeet.saiwatersuppliers.R

class MyMessagingService() : FirebaseMessagingService() {

    private  val TAG = "tag"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.from)

        // Check if message contains a data payload.


        // Check if message contains a data payload.
        //Log.d(TAG, "Message data payload: " + remoteMessage.data)

        val notiTitle = remoteMessage.data.get("title")
        val notiBody = remoteMessage.data.get("body")

        dislayNotification(notiTitle.toString(), notiBody.toString())


    }

    override fun onNewToken(s: String) {
        super.onNewToken(s)

        Log.d(TAG, "Got New Token $s")
    }

    private fun dislayNotification(task: String, desc: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val GeneralChannel = NotificationChannel(
                "General",
                "General",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(GeneralChannel)
        }
        val builder = NotificationCompat.Builder(applicationContext, "General")
            .setContentTitle(task)
            .setContentText(desc)
            .setSmallIcon(R.drawable.logo)
        notificationManager.notify(1, builder.build())
    }


}