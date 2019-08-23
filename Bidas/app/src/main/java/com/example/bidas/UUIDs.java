package com.example.bidas;

import java.util.UUID;

public class UUIDs {

    //Custom service 3 components
    public static UUID CUSTOM_SERVICE_FEE1 = UUID.fromString("0000fee1-0000-1000-8000-00805f9b34fb");
    public static UUID CUSTOM_SERVICE_AUTH_CHARACTERISTIC = UUID.fromString("00000009-0000-3512-2118-0009af100700");
    public static UUID CUSTOM_SERVICE_AUTH_DESCRIPTOR = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    //Device information profile
    public static UUID DEVICE_INFORMATION_SERVICE = UUID.fromString("0000180a-0000-1000-8000-00805f9b34fb");
    public static UUID SERIAL_NUMBER = UUID.fromString("00002a25-0000-1000-8000-00805f9b34fb");
    public static UUID HARDWARE_REVISION_STRING = UUID.fromString("00002a27-0000-1000-8000-00805f9b34fb");
    public static UUID SOFTWARE_REVISION_STRING = UUID.fromString("00002a28-0000-1000-8000-00805f9b34fb");

    //Generic access profile
    public static UUID GENERIC_ACCESS_SERVICE = UUID.fromString("00001800-0000-1000-8000-00805f9b34fb");
    public static UUID DEVICE_NAME_CHARACTERISTIC = UUID.fromString("00002a00-0000-1000-8000-00805f9b34fb");
    public static UUID APPEARANCE_CHARACTERISTIC = UUID.fromString("00002a01-0000-1000-8000-00805f9b34fb");
    public static UUID PERIPHERAL_PREFERRED_CONNECTION_CHARACTERISTIC = UUID.fromString("00002a04-0000-1000-8000-00805f9b34fb");

    //Generic attribute profile
    public static UUID GENERIC_ATTRIBUTE_SERVICE = UUID.fromString("00001801-0000-1000-8000-00805f9b34fb");
    public static UUID SERVICE_CHANGED_CHARACTERISTIC = UUID.fromString("00002a05-0000-1000-8000-00805f9b34fb");
    public static UUID SERVICE_CHANGED_DESCRIPTOR = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    //Space left for custom service 00001530

    //Alert notification profile
    public static UUID ALERT_NOTIFICATION_SERVICE = UUID.fromString("00001811-0000-1000-8000-00805f9b34fb");
    public static UUID NEW_ALERT_CHARACTERISTIC = UUID.fromString("00002a46-0000-1000-8000-00805f9b34fb");
    public static UUID ALERT_NOTIFICATION_CONTROL_POINT = UUID.fromString("00002a44-0000-1000-8000-00805f9b34fb");
    public static UUID ALERT_NOTIFICATION_CONTROL_POINT_DESCRIPTOR = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    //Immediate alert profile
    public static UUID IMMEDIATE_ALERT_SERVICE = UUID.fromString("00001802-0000-1000-8000-00805f9b34fb");
    public static UUID ALERT_LEVEL_CHARACTERISTIC = UUID.fromString("00002a06-0000-1000-8000-00805f9b34fb");

    //Heart rate monitoring profile
    public static UUID HEART_RATE_SERVICE = UUID.fromString("0000180d-0000-1000-8000-00805f9b34fb");
    public static UUID HEART_RATE_MEASUREMENT_CHARACTERISTIC = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");
    public static UUID HEART_RATE_MEASURMENT_DESCRIPTOR = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static UUID HEART_RATE_CONTROL_POINT_CHARACTERISTIC = UUID.fromString("00002a39-0000-1000-8000-00805f9b34fb");


    /*----------------UUID strings for the switch statements----------*/
    public static final String DEVICE_INFORMATION_SERVICE_STRING = "0000180a-0000-1000-8000-00805f9b34fb";
    public static final String CUSTOM_SERVICE_AUTH_CHARACTERISTIC_STRING = "00000009-0000-3512-2118-0009af100700";
    public static final String HEART_RATE_MEASUREMENT_CHARACTERISTIC_STRING = "00002a37-0000-1000-8000-00805f9b34fb";
    public static final String GENERIC_ACCESS_SERVICE_STRING = "00001800-0000-1000-8000-00805f9b34fb";
    public static final String GENERIC_ATTRIBUTE_SERVICE_STRING = "00001801-0000-1000-8000-00805f9b34fb";
    public static final String ALERT_NOTIFICATION_SERVICE_STRING = "00001811-0000-1000-8000-00805f9b34fb";
    public static final String IMMEDIATE_ALERT_SERVICE_STRING = "00001802-0000-1000-8000-00805f9b34fb";



    public static final String MAC_ADDRESS_FILTER = "88:0F:10";

    public static final String BASE_UUID = "0000%s-0000-1000-8000-00805f9b34fb";

    public static final UUID UUID_SERVICE_MIBAND_SERVICE = UUID.fromString(String.format(BASE_UUID, "FEE0"));

    public static final UUID UUID_CHARACTERISTIC_DEVICE_INFO = UUID.fromString(String.format(BASE_UUID, "FF01"));

    public static final UUID UUID_CHARACTERISTIC_DEVICE_NAME = UUID.fromString(String.format(BASE_UUID, "FF02"));

    public static final UUID UUID_CHARACTERISTIC_NOTIFICATION = UUID.fromString(String.format(BASE_UUID, "FF03"));

    public static final UUID UUID_CHARACTERISTIC_USER_INFO = UUID.fromString(String.format(BASE_UUID, "FF04"));

    public static final UUID UUID_CHARACTERISTIC_CONTROL_POINT = UUID.fromString(String.format(BASE_UUID, "FF05"));

    public static final UUID UUID_CHARACTERISTIC_REALTIME_STEPS = UUID.fromString(String.format(BASE_UUID, "FF06"));

    public static final UUID UUID_CHARACTERISTIC_ACTIVITY_DATA = UUID.fromString(String.format(BASE_UUID, "FF07"));

    public static final UUID UUID_CHARACTERISTIC_FIRMWARE_DATA = UUID.fromString(String.format(BASE_UUID, "FF08"));

    public static final UUID UUID_CHARACTERISTIC_LE_PARAMS = UUID.fromString(String.format(BASE_UUID, "FF09"));

    public static final UUID UUID_CHARACTERISTIC_DATE_TIME = UUID.fromString(String.format(BASE_UUID, "FF0A"));

    public static final UUID UUID_CHARACTERISTIC_STATISTICS = UUID.fromString(String.format(BASE_UUID, "FF0B"));

    public static final UUID UUID_CHARACTERISTIC_BATTERY = UUID.fromString(String.format(BASE_UUID, "FF0C"));

    public static final UUID UUID_CHARACTERISTIC_TEST = UUID.fromString(String.format(BASE_UUID, "FF0D"));

    public static final UUID UUID_CHARACTERISTIC_SENSOR_DATA = UUID.fromString(String.format(BASE_UUID, "FF0E"));

    public static final UUID UUID_CHARACTERISTIC_PAIR = UUID.fromString(String.format(BASE_UUID, "FF0F"));

    public static final UUID UUID_DESCRIPTOR_CHARACTERISTIC_USER_CONFIGURATION = UUID.fromString(String.format(BASE_UUID, "2901"));

    public static final UUID UUID_DESCRIPTOR_CLIENT_CHARACTERISTIC_CONFIGURATION = UUID.fromString(String.format(BASE_UUID, "2902"));

    public static final byte ALIAS_LEN = 0xa;

    public static final byte NOTIFY_AUTHENTICATION_FAILED = 0x6;

    public static final byte NOTIFY_AUTHENTICATION_SUCCESS = 0x5;

    public static final byte NOTIFY_CONN_PARAM_UPDATE_FAILED = 0x3;

    public static final byte NOTIFY_CONN_PARAM_UPDATE_SUCCESS = 0x4;

    public static final int NOTIFY_DEVICE_MALFUNCTION = 0xff;

    public static final byte NOTIFY_FIRMWARE_UPDATE_FAILED = 0x1;

    public static final byte NOTIFY_FIRMWARE_UPDATE_SUCCESS = 0x2;

    public static final byte NOTIFY_FITNESS_GOAL_ACHIEVED = 0x7;

    public static final byte NOTIFY_FW_CHECK_FAILED = 0xb;

    public static final byte NOTIFY_FW_CHECK_SUCCESS = 0xc;

    public static final byte NOTIFY_NORMAL = 0x0;

    public static final int NOTIFY_PAIR_CANCEL = 0xef;

    public static final byte NOTIFY_RESET_AUTHENTICATION_FAILED = 0x9;

    public static final byte NOTIFY_RESET_AUTHENTICATION_SUCCESS = 0xa;

    public static final byte NOTIFY_SET_LATENCY_SUCCESS = 0x8;

    public static final byte NOTIFY_STATUS_MOTOR_ALARM = 0x11;

    public static final byte NOTIFY_STATUS_MOTOR_AUTH = 0x13;

    public static final byte NOTIFY_STATUS_MOTOR_AUTH_SUCCESS = 0x15;

    public static final byte NOTIFY_STATUS_MOTOR_CALL = 0xe;

    public static final byte NOTIFY_STATUS_MOTOR_DISCONNECT = 0xf;

    public static final byte NOTIFY_STATUS_MOTOR_GOAL = 0x12;

    public static final byte NOTIFY_STATUS_MOTOR_NOTIFY = 0xd;

    public static final byte NOTIFY_STATUS_MOTOR_SHUTDOWN = 0x14;

    public static final byte NOTIFY_STATUS_MOTOR_SMART_ALARM = 0x10;

    public static final byte NOTIFY_STATUS_MOTOR_TEST = 0x16;

    public static final byte NOTIFY_UNKNOWN = -0x1;

    public static final String UUID_CHARACTERISTIC_FEATURE = "2A9E";

    public static final String UUID_CHARACTERISTIC_MEASUREMENT = "2A9D";

    public static final String UUID_SERVICE_WEIGHT_SCALE_SERVICE = "181D";

    public static final String UUID_SERVICE_WEIGHT_SERVICE = "00001530-0000-3512-2118-0009af100700";

    public static final byte MSG_CONNECTED = 0x0;

    public static final byte MSG_DISCONNECTED = 0x1;

    public static final byte MSG_CONNECTION_FAILED = 0x2;

    public static final byte MSG_INITIALIZATION_FAILED = 0x3;

    public static final byte MSG_INITIALIZATION_SUCCESS = 0x4;

    public static final byte MSG_STEPS_CHANGED = 0x5;

    public static final byte MSG_DEVICE_STATUS_CHANGED = 0x6;

    public static final byte MSG_BATTERY_STATUS_CHANGED = 0x7;

    public static final byte COMMAND_REBOOT = 0xc;

    public static final byte COMMAND_SEND_NOTIFICATION = 0x8;

    public static final byte COMMAND_STOP_MOTOR_VIBRATE = 0x13;

    public static final byte COMMAND_CONFIRM_ACTIVITY_DATA_TRANSFER_COMPLETE = 0xa;

    public static final byte COMMAND_FETCH_DATA = 0x6;

    public static final byte COMMAND_SET_TIMER = 0x4;

}
