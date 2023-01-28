package com.example.rebadge.screens.send

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.util.Log
import java.util.*

@SuppressLint("MissingPermission")
class BluetoothClient(device: BluetoothDevice): Thread() {
    private val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    private val socket = device.createRfcommSocketToServiceRecord(uuid)

    fun connect(messageToSend: String) {
        Log.i("client", "Connecting")
        try {
            this.socket.connect()
        } catch (e: Exception) {
            Log.i("ClientSocket", "SocketIOException")
        }
        //}

        //fun send(message: String) {
        Log.i("client", "Sending")
        val outputStream = this.socket.outputStream
        try {
            outputStream.write(messageToSend.toByteArray())
            outputStream.flush()
            Log.i("client", "Sent")
        } catch (e: Exception) {
            Log.e("client", "Cannot send", e)
        }
//        finally {
//            outputStream.close()
//            this.socket.close()
//        }
    }
}
