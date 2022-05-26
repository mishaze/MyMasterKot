package com.example.mymaster.notification

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseMessagingService : FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        super.onNewToken(token)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }
            val result = task.result
            Log.d(TAG, "Refreshed token: $result")
        }
    }



    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent = Intent(INTENT_FILTER)
        message.data.forEach{ entity ->
            intent.putExtra(entity.key, entity.value)
        }

        sendBroadcast(intent)
    }



    companion object {
        const val INTENT_FILTER ="MESSAGE"
        const val KEY_ACTION="action"
        const val KEY_massage="massage"
        const val ACTION_SHOW_message="show_message"
        private const val TAG = "MyFirebaseMsgService"
    }
}