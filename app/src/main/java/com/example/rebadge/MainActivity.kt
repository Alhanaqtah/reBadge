package com.example.rebadge

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rebadge.navigation.SetUpNavGraph
import com.example.rebadge.ui.theme.ReBadgeTheme

lateinit var btAdapter: BluetoothAdapter

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(
                baseContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                1
            )
        } else {
            Log.d("BluetoothReceiver", "PERMISSION GRANTED")
        }

        if (ContextCompat.checkSelfPermission(
                baseContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else {
            Log.d("BluetoothReceiver", "PERMISSION GRANTED")
        }

        if (ContextCompat.checkSelfPermission(
                baseContext,
                Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                1
            )
        } else {
            Log.d("BluetoothReceiver", "PERMISSION GRANTED")
        }

        if (ContextCompat.checkSelfPermission(
                baseContext,
                Manifest.permission.BLUETOOTH_SCAN
            ) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.BLUETOOTH_SCAN),
                1
            )
        } else {
            Log.d("BluetoothReceiver", "PERMISSION GRANTED")
        }
        
        registerReceiver(btDeviceDiscoveryReceiver, btFilter())

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
                    btAdapter.startDiscovery()
                }
            }
        }
    }
    
    private fun btFilter(): IntentFilter {
        val filter = IntentFilter()
        filter.addAction(BluetoothDevice.ACTION_FOUND)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        return filter
    }

    private val btDeviceDiscoveryReceiver = object : BroadcastReceiver(){
        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action){
                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                    Log.d("BluetoothReceiver","Discovery Started")
                }
                BluetoothDevice.ACTION_FOUND ->{
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    Log.d("BluetoothReceiver" ,"${device?.name}  ${device?.address}")
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    Log.d("BluetoothReceiver","Discovery Finished")
                }
            }
        }
    }

    private fun getBtAdapter(): BluetoothAdapter {
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
    }

    override fun onStop() {
        super.onStop()
        
        unregisterReceiver(btDeviceDiscoveryReceiver)
    }
}
