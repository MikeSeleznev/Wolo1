package com.wolo.a222.feature.common.message

import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.wolo.a222.R

class WoloFirebaseMessagingService : FirebaseMessagingService(){

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        //showhotification(remoteMessage)
        Log.d("TOKEN***", "message")
    }

    private fun showhotification(message: RemoteMessage) {
        //val nM = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val nB = NotificationCompat.Builder(this, "news")
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.wolo)

        val manager = NotificationManagerCompat.from(this)

        manager.notify(1, nB.build())
    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("TOKEN***", token)
    }
}