package com.example.laba5_2.receiver;

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.Toast


private const val TAG = "SMSReceiver"

class SMSReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent.action) {
            for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                val messageBody = smsMessage.messageBody
                if (messageBody.startsWith("PILNE")) {
                    Toast.makeText(context, messageBody, Toast.LENGTH_LONG).show()
                } else if ((1..10).any { messageBody.startsWith(it.toString()) }) {
                    var number = messageBody.first().digitToInt()
                    sendSMS(
                        context,
                        smsMessage.originatingAddress.toString(),
                        (++number).toString()
                    )
                }
            }
        }
    }

    private fun sendSMS(context: Context, phoneNumber: String?, message: String) {
        try {
            val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                context.getSystemService(SmsManager::class.java)
            } else {
                SmsManager.getDefault()
            }
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }
}
