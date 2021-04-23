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

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;
    final String id = "FLAGSMASHER";
    int state = 0;
    TextView messageView;
    TextView senderView;
    TextView stateView;

    boolean disney = false;
    boolean netflix = false;
    boolean hulu = false;

    public void sendMessage(String msg, String to){
        final SmsManager smsManager = SmsManager.getDefault();
     //   int delay = new Random().nextInt(25000 + (1 - 1000)) + 1000;  //min of 1 second delay max of 2.5 seconds

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(id, "Sending Message " + msg + " to: " + to);
                smsManager.sendTextMessage(to, null, msg, null, null);
            }
        }, 2500);
    }


    public Boolean scanForKeywords(String[] keywords, String message ){
        for (String keyword: keywords) {
            if(message.toUpperCase().contains(keyword))
                return true;
        }
        return false;
    }

    public String getRandomMessage(String[] responses ){
        int i = new Random().nextInt(responses.length);
        return responses[i];
    }

    public String getRandomMessage(String responsePrefix, String[] responsesSuffix ){
        int i = new Random().nextInt(responsesSuffix.length);
        return responsePrefix + " " + responsesSuffix[i];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageView = findViewById(R.id.message);
        senderView = findViewById(R.id.sender);
        stateView = findViewById(R.id.state);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(id,"loade on resume");
                Object[] objs = (Object[])intent.getExtras().get("pdus");
                SmsMessage smsArr[] = new SmsMessage[objs.length];
                for(int i =0; i!=objs.length;i++){
                    smsArr[i] = SmsMessage.createFromPdu((byte[])objs[i],intent.getStringExtra("format"));
                    final String address = smsArr[i].getOriginatingAddress();
                    String message = smsArr[i].getMessageBody();
                    Log.d(id,address + " says: "+message);
                    messageView.setText("Recieved Message: " + message);
                    senderView.setText("From: " + address);

                    if(state == 0) {
                        String[] keywordsGreeting = new String[]{"HELLO", "YO", "HI"};
                        String[] responsesGreeting = new String[]{"Up", "Popin", "Good", "you up 2"};

                        if (scanForKeywords(keywordsGreeting, message)) {
                            sendMessage(getRandomMessage("Whats", responsesGreeting), address);
                            state++;
                            stateView.setText("State: " + state);
                        } else {
                            sendMessage("What is you sayin?", address);
                        }
                    }else if(state == 1){
                        String[] keywordsWatch = new String[]{"NOTHING", "NM", "USUAL", "BORED"};
                        String[] responsesWatch = new String[]{"binge?",  "watch?"};

                        if (scanForKeywords(keywordsWatch, message)) {
                            sendMessage(getRandomMessage("Want something to", responsesWatch), address);
                            state++;
                            stateView.setText("State: " + state);
                        } else {
                            sendMessage("What is you sayin?", address);
                        }
                    }else if(state == 2){
                        String[] keywordsYes = new String[]{"SURE", "BET", "YES", "OK"};

                        if (scanForKeywords(keywordsYes, message)) {
                            sendMessage("Whats streaming services do you have? (Disney+, Netflix, Hulu)", address);
                            state++;
                            stateView.setText("State: " + state);

                        }else {
                            sendMessage("You need to say yes", address);
                        }
                    }else if(state >= 4){

                        Boolean alreadyHandled = false;


                        if(message.toUpperCase().contains("ALREADY") || message.toUpperCase().contains("ANOTHER") ||  message.toUpperCase().contains("YES") && state == 5) {
                            state--;
                            stateView.setText("State: " + state);

                        } else if(message.toUpperCase().contains("THANK") && state == 4) {
                            alreadyHandled = true;
                            String[] responsesEnd = new String[]{"np!",  "np", "no problem"};
                            sendMessage(getRandomMessage(responsesEnd), address);
                            sendMessage("If you want another show lmk!", address);
                            state++;
                            stateView.setText("State: " + state);

                        }   else if(message.toUpperCase().contains("ALL")){
                          disney = true;
                          netflix = true;
                          hulu = true;
                      }else{
                          if((message.toUpperCase().contains("DISNEY"))){
                            disney = true;
                          }
                          if((message.toUpperCase().contains("NETFLIX"))){
                              netflix = true;
                          }
                          if((message.toUpperCase().contains("HULU"))){
                              hulu = true;
                          }
                      }

                      if(!disney && !netflix && !hulu){
                          sendMessage("Come on you need to have atleast one of these!", address);
                      }else{
                          ArrayList<String> shows = new ArrayList<String>();
                          ArrayList<String> links = new ArrayList<String>();
                          ArrayList<String> services = new ArrayList<String>();


                          if(disney){
                              //disney

                              String[] disneyShows = new String[]{"Mandolorian",  "Wanda Vision", "Falcon and the Winter Solder"};
                              String[] disneyLinks = new String[]{"https://www.youtube.com/watch?v=eW7Twd85m2g",  "https://www.youtube.com/watch?v=sj9J2ecsSpo", "https://www.youtube.com/watch?v=IWBsDaFWyTE"};

                              Random r = new Random();
                              int rr = r.nextInt(3) ;
                              shows.add(disneyShows[rr]);
                              links.add(disneyLinks[rr]);
                              services.add("Disney+");

                          }
                          if(netflix){
                              //netflix

                              String[] netflixShows = new String[]{"Outer Banks",  "Never Have I Ever", "Tiger King"};
                              String[] netflixLinks = new String[]{"https://www.youtube.com/watch?v=uk_hFfUFXh4",  "https://www.youtube.com/watch?v=HyOCCCbxwMQ", "https://www.youtube.com/watch?v=acTdxsoa428"};

                              Random r = new Random();
                              int rr = r.nextInt(3) ;
                              shows.add(netflixShows[rr]);
                              links.add(netflixLinks[rr]);
                              services.add("Netflix");

                          }
                          if(hulu){
                              //hulu
                              String[] huluShows = new String[]{"Rick and Morty",  "Big Sky", "Game of Thrones"};
                              String[] huluLinks = new String[]{"https://www.youtube.com/watch?v=F6Zy_mLgSNQ",  "https://www.youtube.com/watch?v=1Fs7nfZghow", "https://www.youtube.com/watch?v=rlR4PJn8b8I"};

                              Random r = new Random();
                              int rr = r.nextInt(3) ;
                              shows.add(huluShows[rr]);
                              links.add(huluLinks[rr]);
                              services.add("Hulu");
                          }

                          int j = new Random().nextInt(shows.size());

                          if(!alreadyHandled && state == 3) {
                              state++;
                              stateView.setText("State: " + state);
                              sendMessage("You should watch " + shows.get(j) + " on " + services.get(j) + "! \n" + links.get(j), address);
                          }

                      }
                    }
                }
            }
        };

        String permissionGetSMS = Manifest.permission.RECEIVE_SMS;
        String permissionSendSMS = Manifest.permission.SEND_SMS;

        if ( ContextCompat.checkSelfPermission(this, permissionGetSMS) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, permissionSendSMS) != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[2];

            permission_list[0] = permissionGetSMS;
            permission_list[1] = permissionSendSMS;

            ActivityCompat.requestPermissions(this, permission_list, 1);


        }else{
            IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(receiver,intentFilter);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}
