package com.example.bidas.extra;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.bidas.UUIDs;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BluetoothGattCallbackEx extends BluetoothGattCallback {
    private final Object object = new Object();
    private BluetoothGatt bluetoothGatt;
    private SharedPreferences sharedPreferences;

    public BluetoothGattCallbackEx(SharedPreferences s) {
        this.sharedPreferences = s;
    }

    public void setbluetoothGatt(BluetoothGatt g){
        this.bluetoothGatt = g;
    }
    @Override
    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
        switch (newState) {
            case BluetoothGatt.STATE_DISCONNECTED:
                Log.d("Info", "Device disconnected");

                break;
            case BluetoothGatt.STATE_CONNECTED: {
                Log.d("Info", "Connected with device");
                Log.d("Info", "Discovering services");
                gatt.discoverServices();
            }
            break;
        }
    }

    @Override
    public void onServicesDiscovered(BluetoothGatt gatt, int status) {

        if (status == BluetoothGatt.GATT_SUCCESS) {
            for (BluetoothGattService gattService : gatt.getServices()) {
                Log.d("Service UUID Found: " ,gattService.getUuid().toString());
            }
        }
        if (!sharedPreferences.getBoolean("isAuthenticated", false)) {
            authoriseMiBand();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isAuthenticated", true);
            editor.apply();
        } else
            Log.i("Device", "Already authenticated");
    }

    @Override
    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {

        switch (characteristic.getService().getUuid().toString()) {
            case UUIDs.DEVICE_INFORMATION_SERVICE_STRING:
                handleDeviceInfo(characteristic);
                break;
            case UUIDs.GENERIC_ACCESS_SERVICE_STRING:
                handleGenericAccess(characteristic);
                break;
            case UUIDs.GENERIC_ATTRIBUTE_SERVICE_STRING:
                handleGenericAttribute(characteristic);
                break;
            case UUIDs.ALERT_NOTIFICATION_SERVICE_STRING:
                handleAlertNotification(characteristic);
                break;
            case UUIDs.IMMEDIATE_ALERT_SERVICE_STRING:
                handleImmediateAlert(characteristic);
                break;
        }
    }

    @Override
    public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        super.onCharacteristicWrite(gatt, characteristic, status);
    }

    @Override
    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {

        switch (characteristic.getUuid().toString()) {
            case UUIDs.CUSTOM_SERVICE_AUTH_CHARACTERISTIC_STRING:
                executeAuthorisationSequence(characteristic);
                break;
            case UUIDs.HEART_RATE_MEASUREMENT_CHARACTERISTIC_STRING:
                handleHeartRateData(characteristic);
                break;
        }
    }

    @Override
    public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        Log.d("Descriptor", descriptor.getUuid().toString() + " Read");
    }

    @Override
    public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        Log.d("Descriptor", descriptor.getUuid().toString() + " Written");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void handleGenericAccess(BluetoothGattCharacteristic characteristic) {
        String value = characteristic.getStringValue(0);
        Log.d("TAG", "onCharacteristicRead: " + value + " UUID " + characteristic.getUuid().toString());
        synchronized (object) {
            object.notify();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    /*------Methods to deal with the received data*/
    private void handleDeviceInfo(BluetoothGattCharacteristic characteristic) {
        String value = characteristic.getStringValue(0);
        // Log.d("TAG", "onCharacteristicRead: " + value + " UUID " + characteristic.getUuid().toString());
        synchronized (object) {
            object.notify();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void handleGenericAttribute(BluetoothGattCharacteristic characteristic) {
        String value = characteristic.getStringValue(0);
        Log.d("TAG", "onCharacteristicRead: " + value + " UUID " + characteristic.getUuid().toString());
        synchronized (object) {
            object.notify();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void handleAlertNotification(BluetoothGattCharacteristic characteristic) {
        String value = characteristic.getStringValue(0);
        Log.d("TAG", "onCharacteristicRead: " + value + " UUID " + characteristic.getUuid().toString());
        synchronized (object) {
            object.notify();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void handleImmediateAlert(BluetoothGattCharacteristic characteristic) {
        String value = characteristic.getStringValue(0);
        Log.d("TAG", "onCharacteristicRead: " + value + " UUID " + characteristic.getUuid().toString());
        synchronized (object) {
            object.notify();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void executeAuthorisationSequence(BluetoothGattCharacteristic characteristic) {
        byte[] value = characteristic.getValue();
        if (value[0] == 0x10 && value[1] == 0x01 && value[2] == 0x01) {
            characteristic.setValue(new byte[]{0x02, 0x8});
            bluetoothGatt.writeCharacteristic(characteristic);
        } else if (value[0] == 0x10 && value[1] == 0x02 && value[2] == 0x01) {
            try {
                byte[] tmpValue = Arrays.copyOfRange(value, 3, 19);
                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

                SecretKeySpec key = new SecretKeySpec(new byte[]{0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45}, "AES");

                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] bytes = cipher.doFinal(tmpValue);


                byte[] rq = ArrayUtils.addAll(new byte[]{0x03, 0x8}, bytes);
                characteristic.setValue(rq);
                bluetoothGatt.writeCharacteristic(characteristic);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void handleHeartRateData(final BluetoothGattCharacteristic characteristic) {

        Log.e("Heart",characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0).toString());
        new Runnable() {
            @Override
            public void run() {
                String heartRate = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0).toString();
            }
        };

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void authoriseMiBand() {
        BluetoothGattService service = bluetoothGatt.getService(UUIDs.CUSTOM_SERVICE_FEE1);

        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUIDs.CUSTOM_SERVICE_AUTH_CHARACTERISTIC);
        bluetoothGatt.setCharacteristicNotification(characteristic, true);
        for (BluetoothGattDescriptor descriptor : characteristic.getDescriptors()) {
            if (descriptor.getUuid().equals(UUIDs.CUSTOM_SERVICE_AUTH_DESCRIPTOR)) {
                Log.d("INFO", "Found NOTIFICATION BluetoothGattDescriptor: " + descriptor.getUuid().toString());
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            }
        }

        characteristic.setValue(new byte[]{0x01, 0x8, 0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45});
        bluetoothGatt.writeCharacteristic(characteristic);
    }


};

