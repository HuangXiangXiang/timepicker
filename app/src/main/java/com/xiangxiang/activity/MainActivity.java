package com.xiangxiang.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.xiangxiang.view.TimePicker2;
import com.xiangxiang.wheelview.R;

public class MainActivity extends Activity {

    Button timeBtn ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void showDialog(View view){

        showTimeDialog() ;

//        showWheelView();
    }

    private AlertDialog mAlertDialog ;

    private void showTimeDialog(){


        mAlertDialog = new AlertDialog.Builder(this).create();

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_show_time,null) ;
        mAlertDialog.setView(view);


        final EditText timerOn = (EditText) view.findViewById(R.id.dst_time_on_edt);
        final EditText timerOff = (EditText) view.findViewById(R.id.dst_time_off_edt);

        timerOn.setInputType(InputType.TYPE_NULL);
        timerOff.setInputType(InputType.TYPE_NULL);

        timerOn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                showTimePick((EditText)v);
            }
        });
        timerOff.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                showTimePick((EditText)v);
            }
        });

        timerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePick((EditText)v);
            }
        });
        timerOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePick((EditText)v);
            }
        });

        mAlertDialog.show();
    }





    private void showTimePick (final EditText timerEdt){

        View view = new TimePicker2(this).getView();

        PopupWindow pw = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        pw.setContentView(view);
        pw.setOutsideTouchable(true);
        pw.setFocusable(false);

        pw.showAtLocation(timerEdt , Gravity.BOTTOM,0,-1000);
//        pw.showAsDropDown(timerEdt,0,100 , Gravity.BOTTOM);
    }




    private void showWheelView(){
        View view = new TimePicker2(this).getView();

        // 展示弹出框
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("WheelView in Dialog").setView(view)
                .setPositiveButton("OK", null).show();

    }

}
