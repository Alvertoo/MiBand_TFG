package com.example.bidas.extra;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.bidas.UUIDs;

public class BluetoothInitServices {
    private BluetoothGattService variableService;
    private BluetoothGatt bluetoothGatt;
    private final Object object = new Object();


    public BluetoothInitServices(BluetoothGattService variableService,BluetoothGatt bluetoothGatt){
        this.bluetoothGatt=bluetoothGatt;
        this.variableService=variableService;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void getGenericAccessInfo() {
        variableService = bluetoothGatt.getService(UUIDs.GENERIC_ACCESS_SERVICE);
        try {
            for (BluetoothGattCharacteristic characteristic : variableService.getCharacteristics()) {
                bluetoothGatt.setCharacteristicNotification(characteristic, true);
                bluetoothGatt.readCharacteristic(characteristic);
                synchronized (object) {
                    object.wait(5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //getting the device details
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void getDeviceInformation() {
        variableService = bluetoothGatt.getService(UUIDs.DEVICE_INFORMATION_SERVICE);

        try {
            for (BluetoothGattCharacteristic characteristic : variableService.getCharacteristics()) {
                bluetoothGatt.setCharacteristicNotification(characteristic, true);
                bluetoothGatt.readCharacteristic(characteristic);
                synchronized (object) {
                    object.wait(5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void genericAttribute() {
        variableService = bluetoothGatt.getService(UUIDs.GENERIC_ATTRIBUTE_SERVICE);
        try {
            for (BluetoothGattCharacteristic characteristic : variableService.getCharacteristics()) {
                bluetoothGatt.setCharacteristicNotification(characteristic, true);
                bluetoothGatt.readCharacteristic(characteristic);
                synchronized (object) {
                    object.wait(5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void alertNotification() {
        variableService = bluetoothGatt.getService(UUIDs.ALERT_NOTIFICATION_SERVICE);
        try {
            for (BluetoothGattCharacteristic characteristic : variableService.getCharacteristics()) {
                bluetoothGatt.setCharacteristicNotification(characteristic, true);
                bluetoothGatt.readCharacteristic(characteristic);
                synchronized (object) {
                    object.wait(5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void immediateAlert() {
        variableService = bluetoothGatt.getService(UUIDs.IMMEDIATE_ALERT_SERVICE);
        try {
            for (BluetoothGattCharacteristic characteristic : variableService.getCharacteristics()) {
                bluetoothGatt.setCharacteristicNotification(characteristic, true);
                bluetoothGatt.readCharacteristic(characteristic);
                synchronized (object) {
                    object.wait(5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
