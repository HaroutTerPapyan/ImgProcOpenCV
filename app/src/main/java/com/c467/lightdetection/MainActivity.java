package com.c467.lightdetection;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    SensorManager mySensorManager;

    TextView txtVals;
    ImageView img;

    TTSHelper tts;

    int mNumDrawables = 100;
    Drawable[] mImgArr = new Drawable[mNumDrawables];
    int icons[] = new int[100];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TTSHelper(this);
        img = (ImageView) findViewById(R.id.image);
        txtVals = (TextView) findViewById(R.id.txtVals);
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor LightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(LightSensor != null){
            mySensorManager.registerListener(
                    LightSensorListener,
                    LightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }


    }


    public void processImages(View v) {
        //img.setImageDrawable(mImgArr[1]);
        Intent intent = new Intent(this, Image.class);
        this.startActivity(intent);

    }



    private final SensorEventListener LightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                int i = 0;
                float vals = event.values[0];
                if(vals <= 100) {
                    img.setImageResource(R.drawable.black);
                    txtVals.setText(String.valueOf(vals));
                    if (i == 100) i = 0;
                    icons[i] = R.drawable.black;
                    i++;
                } else if(vals > 100 && vals <= 200) {
                    img.setImageResource(R.drawable.grey1);
                    txtVals.setText(String.valueOf(vals));
                    //tts.speakText("Grey");
                    if (i == 100) i = 0;
                    icons[i] = R.drawable.grey1;
                    i++;
                } else if(vals > 200 && vals <= 300) {
                    img.setImageResource(R.drawable.grey2);
                    txtVals.setText(String.valueOf(vals));
                    //tts.speakText("Grey");
                    if (i == 100) i = 0;
                    icons[i] = R.drawable.grey2;
                    i++;
                } else if(vals > 300 && vals <= 400) {
                    img.setImageResource(R.drawable.grey3);
                    txtVals.setText(String.valueOf(vals));
                    //tts.speakText("Grey");
                    if (i == 100) i = 0;
                    icons[i] = R.drawable.grey3;
                    i++;
                } else if(vals > 400) {
                    img.setImageResource(R.drawable.white);
                    txtVals.setText(String.valueOf(vals));
                    //tts.speakText("White");
                    if (i == 100) i = 0;
                    icons[i] = R.drawable.white;
                    i++;
                }
            }
        }

    };

}



// install opencv in android http://stackoverflow.com/questions/27406303/opencv-in-android-studio