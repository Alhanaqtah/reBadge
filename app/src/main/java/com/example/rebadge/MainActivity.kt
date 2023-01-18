package com.example.rebadge

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rebadge.navigation.SetUpNavGraph
import com.example.rebadge.ui.theme.ReBadgeTheme

lateinit var btAdapter: BluetoothAdapter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var navController: NavController

        setContent {
            ReBadgeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    SetUpNavGraph(rememberNavController())

                    btAdapter = getBtAdapter()
                    enableBluetooth(btAdapter)
                }
            }
        }
    }

    fun getBtAdapter(): BluetoothAdapter {
        val btManager = this.getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        return btManager.adapter
    }

    fun enableBluetooth(btAdapter: BluetoothAdapter) {
        if (!btAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                startActivityForResult(enableBtIntent, 1)
            }
        }
        Log.d("Log", "Bluetooth Connect Request")
    }
}
