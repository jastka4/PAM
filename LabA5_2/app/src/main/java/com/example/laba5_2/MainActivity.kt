package com.example.laba5_2

import android.Manifest
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.laba5_2.receiver.SMSReceiver

val smsReceiver = SMSReceiver()

class MainActivity : AppCompatActivity() {
    companion object {
        private const val MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10
        private const val MY_PERMISSIONS_REQUEST_SEND_SMS = 1

        var permissions = mapOf(
            Manifest.permission.RECEIVE_SMS to MY_PERMISSIONS_REQUEST_SMS_RECEIVE,
            Manifest.permission.SEND_SMS to MY_PERMISSIONS_REQUEST_SEND_SMS
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestSMSPermissions()
        registerReceiver(smsReceiver, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(smsReceiver)
    }

    private fun requestSMSPermissions() {
        for (permission in permissions) {
            when {
                ContextCompat.checkSelfPermission(
                    this, permission.key
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                }

                ActivityCompat.shouldShowRequestPermissionRationale(
                    this, permission.key
                ) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected, and what
                    // features are disabled if it's declined. In this UI, include a
                    // "cancel" or "no thanks" button that lets the user continue
                    // using your app without granting the permission.
                }

                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    ActivityCompat.requestPermissions(
                        this, arrayOf(permission.key), permission.value
                    );
                }
            }
        }
    }

}