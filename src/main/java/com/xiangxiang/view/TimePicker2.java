package com.xiangxiang.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TimePicker;


import com.xiangxiang.timepicker.R;
import com.xiangxiang.view.wheelview.ArrayWheelAdapter;
import com.xiangxiang.view.wheelview.NumericWheelAdapter;
import com.xiangxiang.view.wheelview.OnWheelChangedListener;
import com.xiangxiang.view.wheelview.WheelView;

import java.util.ArrayList;

/**
 * Created by xiangxiang on 2017/3/31.
 */

public class TimePicker2 extends PopupWindow implements OnWheelChangedListener {

    private View mView ;

    private WheelView mHourWvw ;
    private WheelView mMinuteWvw ;
    private WheelView mAmPmWvw ;

    private int hour ;
    private int minute ;
    private int amPm;

    private OnTimeChangedListener mOnTimeChangedListener ;

    public TimePicker2(Context context){

        mView = LayoutInflater.from(context).inflate(R.layout.wheel_view, null);

        mHourWvw = (WheelView) mView.findViewById(R.id.hour_wvw);
        NumericWheelAdapter hourNwa = new NumericWheelAdapter(1,12) ;
        mHourWvw.setAdapter(hourNwa);
        mHourWvw.addChangingListener(this) ;

        mMinuteWvw = (WheelView) mView.findViewById(R.id.minute_wvw);
        NumericWheelAdapter minuteNwa = new NumericWheelAdapter(0,59) ;
        mMinuteWvw.setAdapter(minuteNwa);
        mMinuteWvw.addChangingListener(this) ;

        mAmPmWvw = (WheelView) mView.findViewById(R.id.am_pm_wvw);
        ArrayList al = new ArrayList() ;
        al.add("AM");
        al.add("PM");
        ArrayWheelAdapter amPmNwa = new ArrayWheelAdapter(al) ;
        mAmPmWvw.setAdapter(amPmNwa);
        mAmPmWvw.addChangingListener(this) ;

    }

    public View getView(){

        return mView ;
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {

        if(wheel == mHourWvw){

            this.hour = newValue ;

        }else if(wheel == mMinuteWvw){

            this.minute = newValue ;

        }else if(wheel == mAmPmWvw){

            this.amPm = newValue ;
        }


        if(mOnTimeChangedListener != null){

            mOnTimeChangedListener.onTimeChanged((hour + amPm*12) , minute);
        }
    }


    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener){

        this.mOnTimeChangedListener = onTimeChangedListener ;
    }


    public interface OnTimeChangedListener{

        void onTimeChanged(int hourOfDay, int minute);
    }

}
