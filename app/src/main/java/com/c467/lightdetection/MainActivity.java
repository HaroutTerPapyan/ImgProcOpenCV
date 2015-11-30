package com.c467.lightdetection;

import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
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


    public void processImages() {

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
                System.out.println("Values : " + vals);
                if(vals <= 50) {
                    img.setImageResource(R.drawable.black);
                    txtVals.setText(String.valueOf(vals));
                    //tts.speakText("Black");
                    //mImgArr[i] =
                    icons[i] = R.drawable.black;
                    i++;
                } else if(vals > 50 && vals <= 200) {
                    img.setImageResource(R.drawable.grey);
                    txtVals.setText(String.valueOf(vals));
                    //tts.speakText("Grey");
                    icons[i] = R.drawable.grey;
                    i++;
                } else if(vals > 200) {
                    img.setImageResource(R.drawable.white);
                    txtVals.setText(String.valueOf(vals));
                    //tts.speakText("White");
                    icons[i] = R.drawable.white;
                    i++;
                }
            }
        }

    };

}



// install opencv in android http://stackoverflow.com/questions/27406303/opencv-in-android-studio