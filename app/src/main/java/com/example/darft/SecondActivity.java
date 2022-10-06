package com.example.darft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_display;
    private static final String TAG = "SecondActivity";
    private String winningTeam;
    private String strScoreCount;
    private String result;
    private Button locButton, callButton, messageButton;
    private EditText editTextLoc;
    public static final int REQUEST_CALL_PHONE = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "inside onCreate");

        tv_display = (TextView)findViewById(R.id.tv_display2);

        Intent intent = getIntent();
        strScoreCount = intent.getExtras().get(MainActivity.END_RESULT).toString();
        winningTeam = intent.getExtras().get(MainActivity.WINNING_TEAM).toString();
        result = winningTeam + " ";
        tv_display.setText(winningTeam);
        tv_display.append(" ");

        getReferencetoWidgets();
        setOnClickListenersForButtons();



        Log.d(TAG, "end of onCreate");
    }

    private void setOnClickListenersForButtons() {
        Log.d(TAG,"inside of setOnClickListenersForButtons");
        callButton.setOnClickListener(this);
        locButton.setOnClickListener(this);
        messageButton.setOnClickListener(this);



        Log.d(TAG,"end of setOnClickListenersForButtons");
    }

    private void getReferencetoWidgets() {
        Log.d(TAG,"inside of getReferencesToWidgets");
        callButton=findViewById(R.id.button);
        locButton=findViewById(R.id.button3);
        messageButton=findViewById(R.id.button2);
        editTextLoc=findViewById(R.id.editTextLoc);


        Log.d(TAG,"end of getReferencesToWidgets");
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"inside of onClick");
        switch(v.getId()){
            case R.id.button:
                Log.d(TAG,"Call button clicked");
                makeCall();
                break;
            case R.id.button2:
                Log.d(TAG,"Text button clicked");
                sendMessage();
                break;
            case R.id.button3:
                Log.d(TAG,"Location button clicked");
                findLocations();
                break;

        }

        Log.d(TAG,"end of onClick");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "inside onSaveInstanceState");
        outState.putString("result", winningTeam);
        outState.putString("wonBy", strScoreCount);
        Log.d(TAG, "end of onSaveInstanceState message = "+ winningTeam);
        Log.d(TAG, "end of onSaveInstanceState score = " + strScoreCount);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        winningTeam = savedInstanceState.getString("result", winningTeam);
        Log.d(TAG, "end of onRestoreInstanceState");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "inside of onStart");

        Log.d(TAG, "end of onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "inside of onResume");
        tv_display.setText(result);
        Log.d(TAG, "end of onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "inside of onRestart");

        Log.d(TAG, "end of onRestart");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "inside of onPause");

        Log.d(TAG, "end of onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "inside of onStop");

        Log.d(TAG, "end of onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "inside of onDestroy");

        Log.d(TAG, "end of onDestroy");
    }

    public void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:2128675389"));

        //Intent chooser = Intent.createChooser(intent, "Call with");
        if(intent.resolveActivity(getPackageManager()) != null){
            //startActivity(intent);
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                Log.d(TAG, "Need permission to contact a number");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);

            }
            else{
                startActivity(intent);
            }
        }else{
            Log.d(TAG, "Can't place a phone call at this moment");

        }

        Log.d(TAG, "End of the makecall function");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG,"inside of onRequestPermissionsResult");
        switch (requestCode){
            case REQUEST_CALL_PHONE:{
                if((grantResults.length>9) &&(grantResults[0]== PackageManager.PERMISSION_GRANTED ) ){
                    makeCall();
                }
                else{
                    Log.d(TAG,"need CALL_PHONE permission");
                    Toast.makeText(this, "need CALL_PHONE permission",Toast.LENGTH_LONG).show();
                }
            }

        }


    }

    public void sendMessage(){
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, result);
        intent.setType("text/plain");
        Intent chooser = Intent.createChooser(intent, "Share with");
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(chooser);
        }
    }

    public void findLocations(){

        String loc = editTextLoc.getText().toString();
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+loc);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        //mapIntent.setPackage("com.google.android.apps.maps");
        //Intent chooser = Intent.createChooser(mapIntent, "Find Location With");
        if(mapIntent.resolveActivity(getPackageManager())!= null) {
            startActivity(mapIntent);
        }else{
            Log.d(TAG, "Can't find location");
            Toast.makeText(this, "Cannot find location"+"",Toast.LENGTH_LONG).show();
        }
    }
}