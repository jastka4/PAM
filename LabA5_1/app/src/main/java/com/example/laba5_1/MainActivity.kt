package com.example.laba5_1

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.laba5_1.receiver.BatteryLevelReceiver
import com.example.laba5_1.receiver.PowerConnectionReceiver

val batteryLevelReceiver = BatteryLevelReceiver()
val powerConnectionReceiver = PowerConnectionReceiver()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerReceiver(batteryLevelReceiver, IntentFilter(Intent.ACTION_BATTERY_LOW))
        registerReceiver(batteryLevelReceiver, IntentFilter(Intent.ACTION_BATTERY_OKAY))
        registerReceiver(powerConnectionReceiver, IntentFilter(Intent.ACTION_POWER_CONNECTED))
        registerReceiver(powerConnectionReceiver, IntentFilter(Intent.ACTION_POWER_DISCONNECTED))

        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryLevelReceiver)
        unregisterReceiver(powerConnectionReceiver)
    }
}