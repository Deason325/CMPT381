package com.example.nan.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView cv1 = findViewById(R.id.cv1);


        final EditText et = findViewById(R.id.pw);
        et.setText("");
        View.OnTouchListener touch = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me) {
                if (me.getX()<=400&&me.getX()>=100 && me.getY()>=300&&me.getY()<=500){

                    et.setText("click!");
                }
                return false;
            }
        };
        cv1.setOnTouchListener(touch);

    }





}
