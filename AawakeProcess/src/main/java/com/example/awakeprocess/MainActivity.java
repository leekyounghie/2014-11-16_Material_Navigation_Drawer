package com.example.awakeprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Broadcast가 실행되면 AwakeReceiver에게 알려라
        Intent boradcastIntent = new Intent(AwakeReceiver.ACTION_START);
        sendBroadcast(boradcastIntent);
    }
}

