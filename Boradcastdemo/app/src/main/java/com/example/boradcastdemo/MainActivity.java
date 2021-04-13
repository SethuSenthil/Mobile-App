package com.example.boradcastdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;
    String state = "Awaiting greeting";
    final int delay = 0;
    final String id = "FLAGSMASHER";

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(id,"res");
                Object[] objs = (Object[])intent.getExtras().get("pdus");
                SmsMessage smsArr[] = new SmsMessage[objs.length];
                final SmsManager man = SmsManager.getDefault();
                for(int i =0; i!=objs.length;i++){
                    smsArr[i] = SmsMessage.createFromPdu((byte[])objs[i],intent.getStringExtra("format"));
                    final String address = smsArr[i].getOriginatingAddress();
                    String message = smsArr[i].getMessageBody();
                    Log.d(id,"message: "+message);

                    if((message.toLowerCase().contains("yo"))) {
                        sendMessage("what up", address);
                    }
                    //((TextView)findViewById(R.id.output)).setText(state);
                }
            }
        };

        String permission = Manifest.permission.RECEIVE_SMS;
        String permission2 = Manifest.permission.SEND_SMS;
        if ( ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, permission2) != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[2];
            permission_list[0] = permission;
            permission_list[1] = permission2;
            ActivityCompat.requestPermissions(this, permission_list, 1);


        }else{
            IntentFilter inF = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(receiver,inF);
            Log.d(id,"reg");
        }


    }

    public void sendMessage(final String sendMessage,final String address){
        final SmsManager man = SmsManager.getDefault();
        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(id, "test");
                man.sendTextMessage(address, null, sendMessage, null, null);
            }
        }, delay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}
