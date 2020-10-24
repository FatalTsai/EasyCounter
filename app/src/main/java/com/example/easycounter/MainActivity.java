package com.example.easycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener,View.OnLongClickListener{

    TextView txv;
    Button btn;
    int counter = 0;
    private float x1,x2;
    static final int MIN_DISTANCE = 150;



    @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv =(TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.add);

        btn.setOnClickListener(this);
        btn.setOnLongClickListener(this);
        txv.setOnLongClickListener(this);
    }

    @Override
    public  void onClick(View v){
        txv.setText(String.valueOf(++counter));
    }


    public void minus(View v){
        txv.setText(String.valueOf(--counter));
    }

    public  void finish(View v){
        MainActivity.this.finish();
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.textView) {
            counter = 0;
            txv.setText("0");
        }

        else {
            counter+=2;
            txv.setText(String.valueOf(counter));
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    //ref from:https://stackoverflow.com/questions/6645537/how-to-detect-the-swipe-left-or-right-in-android?fbclid=IwAR2lQ5dvUe3XrpaEcyyJW9HmNfhcagynlLTsB-1OGf1t17aVr3XNIrgdSzU
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    if (x2 > x1)
                    {
                        counter-=2;
                        txv.setText(String.valueOf(counter));

                    }

                    // Right to left swipe action
                    else
                    {
                        counter+=2;
                        txv.setText(String.valueOf(counter));
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }

}
