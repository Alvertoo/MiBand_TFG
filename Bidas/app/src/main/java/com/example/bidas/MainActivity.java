package com.example.bidas;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.bidas.bbdd.BDDeviceData;
import com.example.bidas.entities.deviceData;
import com.example.bidas.extra.BluetoothGattCallbackEx;
import com.example.bidas.extra.BluetoothInitServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattService variableService;
    private BluetoothGattCallbackEx miBandGattCallBack;
    private final Object object = new Object();
    private BluetoothInitServices initServices;


    // Seleccion de menus inferiores
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Inicializa los componentes
        initComponents();
    }

    // Inicializa los componentes
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initComponents() {
        Button getBandDetails = (Button) findViewById(R.id.btnGetDetails);
        getBandDetails.setEnabled(false);
        validateDevice();
        getPermissions();
    }

    // Comprueba si ya hay un dispositivo vinculado
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void validateDevice() {
        BDDeviceData dd = new BDDeviceData(this);
        String idDevice = dd.getDeviceData();

        if (idDevice != "null"){
            Button getBandDetails = (Button) findViewById(R.id.btnGetDevices);
            getBandDetails.setEnabled(false);
            initialiseViewsAndComponents();
            enableBTAndDiscover(idDevice);
        }
    }

    // Accion del boton buscar
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public void getDevicesBle(View view)
    {
        initialiseViewsAndComponents();
        enableBTAndDiscover("");
    }



    // Vincula un dispositivo
    public void saveDevice(String name, String id) {
        BDDeviceData dd = new BDDeviceData(this);
        deviceData device = new deviceData(id,name);
        dd.insertDeviceData(device);
    }

    // Pide permisos para el funcionamiento
    private void getPermissions() {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_DENIED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    shouldShowRequestPermissionRationale("Por favor acepta los permisos para que funcione correctamente!");
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH}, 1);
                }
            }
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission_group.LOCATION) == PackageManager.PERMISSION_DENIED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    shouldShowRequestPermissionRationale("Por favor acepta los permisos para que funcione correctamente!");
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            }

        } else {
            Toast.makeText(MainActivity.this, "Este dispositivo no soporta la conexión bluetooth", Toast.LENGTH_LONG).show();
        }
    }

    // Obtiene la caracteristica
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private BluetoothGattCharacteristic getCharacteristics(UUID service, UUID characteristic) {
        try {
            variableService = bluetoothGatt.getService(UUIDs.GENERIC_ACCESS_SERVICE);
            BluetoothGattCharacteristic name = variableService.getCharacteristic(characteristic);
            return name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Accion del boton detalles
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void getDetails(View view)
    {
        TextView deviceName = (TextView) findViewById(R.id.deviceName);
        initServices = new BluetoothInitServices(variableService,bluetoothGatt);
        initServices.getDeviceInformation();
        initServices.getGenericAccessInfo();
        // Obtiene el componente
        BluetoothGattCharacteristic name = getCharacteristics(UUIDs.GENERIC_ACCESS_SERVICE,UUIDs.DEVICE_NAME_CHARACTERISTIC);
        if (name != null && name.getValue() != null){
            deviceName.setText(new String(name.getValue()));
        }else {
            deviceName.setText("Error en la conexion con el dispositivo");

        }

    }

    // Busca y se conecta al dispositivo
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableBTAndDiscover(final String id ) {
        final BluetoothAdapter bluetoothAdapter = ((BluetoothManager) getSystemService(BLUETOOTH_SERVICE)).getAdapter();
        final ArrayList<BluetoothDevice> deviceArrayList = new ArrayList<BluetoothDevice>();

        final ProgressDialog searchProgress = new ProgressDialog(MainActivity.this);
        searchProgress.setIndeterminate(true);
        searchProgress.setTitle("Conexión Bluetooth");
        searchProgress.setMessage("Buscando dispositivos...");
        searchProgress.setCancelable(false);
        searchProgress.show();

        if (bluetoothAdapter == null) {
            Toast.makeText(MainActivity.this, "Bluetooth no soportado en este dispositivo", Toast.LENGTH_LONG).show();
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1);

        }
        final ScanCallback leDeviceScanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                if (!deviceArrayList.contains(result.getDevice())) {
                    deviceArrayList.add(result.getDevice());
                    // Comprueba para conectarse dependiendo de si ya tiene uno vinculado o no
                    if( result.getDevice().getName()!=null &&
                            ( (result.getDevice().getName().contains("Band") && id.isEmpty())
                                    || result.getDevice().getAddress().equals(id))  ) {
                        connectDevice((BluetoothDevice) result.getDevice());
                        if (id.isEmpty()) { saveDevice(result.getDevice().getName(),result.getDevice().getAddress());}
                    }
                }
            }

            @Override
            public void onScanFailed(int errorCode) {
                Toast.makeText(MainActivity.this, "No se ha encontrado el dispositivo", Toast.LENGTH_LONG).show();
            }
        };

        bluetoothAdapter.getBluetoothLeScanner().startScan(leDeviceScanCallback);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bluetoothAdapter.getBluetoothLeScanner().stopScan(leDeviceScanCallback);
                searchProgress.dismiss();

                mTextMessage.setText("Dispositivo conectado.");
                Button getBandDetails = (Button) findViewById(R.id.btnGetDetails);
                getBandDetails.setEnabled(true);
            }
        }, 20000);
    }


    //Se conecta a la app
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void connectDevice(BluetoothDevice miBand) {

        if (miBand.getBondState() == BluetoothDevice.BOND_NONE) {
            miBand.createBond();
            Log.d("Bond", "Created with Device");
        }
        bluetoothGatt = miBand.connectGatt(getApplicationContext(), true, miBandGattCallBack);
        miBandGattCallBack.setbluetoothGatt(bluetoothGatt);
    }

    //Inicializa la conexión por bluetooth
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initialiseViewsAndComponents() {
        SharedPreferences sharedPreferences = getSharedPreferences("MiBandConnectPreferences", Context.MODE_PRIVATE);
        miBandGattCallBack = new BluetoothGattCallbackEx(sharedPreferences);
    }

}
