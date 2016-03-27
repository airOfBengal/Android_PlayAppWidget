package com.airofbengal.playappwidget;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {
    private static final String[] timeUnits = {"Minute","Hour","Second"};
    private static AlarmManager alarmManager = null;
    private static PendingIntent pendingIntent = null;
    private EditText editTextTime;
    private Spinner spinnerTimeUnits;
    private static AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        //widgetID = getIntent().getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,0);
        editTextTime = (EditText)findViewById(R.id.etTime);
        spinnerTimeUnits = (Spinner) findViewById(R.id.spinnerTimeUnits);
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
    }

    public void onCancel(View view) {
        finish();
    }

    public void onSet(View view) {
        String time = editTextTime.getText().toString();
        if(time == null || time.equals("")) return;
        int intTime;
        try{
            intTime = Integer.valueOf(time);
        }catch (Exception e){
            return;
        }

        String timeUnit = timeUnits[spinnerTimeUnits.getSelectedItemPosition()];

        Intent intent = new Intent(this,MyBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(),23456734,intent,0);

        if(alarmManager != null) {
            switch (timeUnit){
                case "Minute":
                    intTime = (intTime * 1000 * 60);
                    break;
                case "Hour":
                    intTime = (intTime * 1000 * 60 * 60);
                    break;
                default:
                    intTime = (intTime * 1000);
            }
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + intTime, pendingIntent);
        }
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        finish();
    }
}
