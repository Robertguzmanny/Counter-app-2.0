package com.example.darft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView gameCount;
    private TextView otherCount;
    private int myCounter = 0;
    private int theirCounter = 0;
    private static final String TAG = "MainActivity";
    protected static final String END_RESULT = "End Result";
    protected static final String WINNING_TEAM = "Who Won";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameCount = (TextView) findViewById(R.id.show_count);
        otherCount = (TextView) findViewById(R.id.show_enemyCount);


        gameCount.setText(""+myCounter);
        otherCount.setText(""+theirCounter);
        Log.d(TAG, "end of onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "inside onSaveInstanceState");
        outState.putInt("myCount", myCounter);
        outState.putInt("theirCount", theirCounter);

        Log.d(TAG, "end of onSaveInstanceState myCounter ="+ myCounter);
        Log.d(TAG, "end of onSaveInstanceState theirCounter ="+ theirCounter);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myCounter = savedInstanceState.getInt("myCount", myCounter);
        theirCounter = savedInstanceState.getInt("theirCount", theirCounter);
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
        gameCount.setText(""+myCounter);
        otherCount.setText(""+theirCounter);
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

    public void countUp(android.view.View view) {
        myCounter++;
        gameCount.setText(""+ myCounter);
        nextActivity();
    }

    public void eCountUp(android.view.View view) {
        theirCounter++;
        otherCount.setText(""+ theirCounter);
        nextActivity();
    }

    public void nextActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        int result;
        if(myCounter == 5){
            result = myCounter - theirCounter;
            intent.putExtra(END_RESULT, result);
            intent.putExtra(WINNING_TEAM, "Your Team Won By:");
            startActivity(intent);
        }
        else if(theirCounter == 5) {
            result = theirCounter - myCounter;
            intent.putExtra(END_RESULT, result);
            intent.putExtra(WINNING_TEAM, "Your Team Lost By:");
            startActivity(intent);
        }
    }

}