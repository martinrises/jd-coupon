package com.odev.yhq_getter;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_STR = "body=%7B%22activityId%22%3A%2241aqaSvcZnV7XDwDXm1NtiHiqz7J%22%2C%22from%22%3A%22H5node%22%2C%22scene%22%3A%221%22%2C%22args%22%3A%22key%3D297e27e0b46c4af69b2abe915a9ddf27%2CroleId%3D15012289%22%2C%22platform%22%3A%223%22%2C%22orgType%22%3A%222%22%2C%22openId%22%3A%22-1%22%2C%22pageClickKey%22%3A%22Babel_Coupon%22%2C%22eid%22%3A%22CQK7PEMEARUO45WX454C2XS2BSQFTVJ7LGHA7UQE3AHAGQIYCOVTVKNVSE5GGZ3GDXPSRLIHNLZ4XUYC3XE2ERPZ6U%22%2C%22fp%22%3A%226ab2735ff7bf092ac2ecd518b13c87cf%22%2C%22shshshfp%22%3A%22e1fe31b1ea1c14a949e93a2e1fc6dcf4%22%2C%22shshshfpa%22%3A%227c7ff8fb-4e5f-0226-66f1-789cd36ce324-1540483398%22%2C%22shshshfpb%22%3A%2214d6ce9f499d1479f949a60727c5877381393786c669488245bd1e9483%22%2C%22childActivityUrl%22%3A%22https%253A%252F%252Fpro.m.jd.com%252Fmall%252Factive%252F41aqaSvcZnV7XDwDXm1NtiHiqz7J%252Findex.html%253Futm_source%253Dpdappwakeupup_20170001%2526ShareTm%253DrChy88z654oe3N78RDkyVtbXv4u1BvFXsw2RbPGSSpOldf9IZcvqcSWYppbA84OiL98%25252FtP6Wa8KXIcNHUQVrTb8Y5Vh4bI%25252FPVO4%25252BDl59jTFjRF4FKUnjXC%25252F2yBEp3t6cOBS3CwTn%25252BawfYgoYTP9cpSH6nnqeEGX3twznhtfG%25252FBU%25253D%2526ad_od%253Dshare%2526utm_source%253Dandroidapp%2526utm_medium%253Dappshare%2526utm_campaign%253Dt_335139774%2526utm_term%253DWxfriends%2526from%253Dsinglemessage%22%2C%22mitemAddrId%22%3A%22%22%2C%22geo%22%3A%7B%22lng%22%3A%22%22%2C%22lat%22%3A%22%22%7D%2C%22addressId%22%3A%22%22%2C%22posLng%22%3A%22%22%2C%22posLat%22%3A%22%22%2C%22focus%22%3A%22%22%2C%22innerAnchor%22%3A%22%22%7D&client=wh5&clientVersion=1.0.0&sid=&uuid=1540483397320591160689&area=";


    private static final int MSG_YHQ = 96;

    private Handler mHandler;
    private boolean mIsRunning;
    private long mStartTs, mIntervalMillis;
    private int mCnt, mSumMaxCnt, mCurrSumCnt;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HandlerThread t = new HandlerThread("yhq");
        t.start();
        mHandler = new Handler(t.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_YHQ:
                        long diff = mStartTs - System.currentTimeMillis();
                        if (diff  <= 0) {
                            int cnt = 0;
                            while(cnt < mCnt && mCurrSumCnt < mSumMaxCnt) {
                                doYhq();
                                cnt++;
                                mCurrSumCnt++;
                            }
                        } else {
                            Log.i("yhq", "running diff >>> " + diff);
                        }
                        synchronized (MainActivity.this) {
                            if (mIsRunning) {
                                long timeDelay = 20 + (int)(Math.random() * mIntervalMillis);
                                mHandler.sendEmptyMessageDelayed(MSG_YHQ, timeDelay);
                            }
                        }
                        break;
                }
            }
        };

        mBtn = findViewById(R.id.start_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (MainActivity.this) {
                    if(mIsRunning) {
                        stopYhq();
                        mBtn.setText("start");
                    } else {
                        startYhq();
                        mBtn.setText("stop");
                    }
                }
            }
        });
    }

    private void doYhq() {
        new Thread(){
            @Override
            public void run() {
                MediaType mediaTypeJson = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
                RequestBody body = RequestBody.create(mediaTypeJson, JSON_STR);
                Disposable newBabelAwardCollection = RetrofitManager.getOrderService().yhq("newBabelAwardCollection", body)
                        .subscribe(new Consumer<BaseResult>() {
                            @Override
                            public void accept(BaseResult baseResult) throws Exception {
                                Log.i("doYhq", baseResult.getSubCodeMsg());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("doYhq", throwable.toString());
                            }
                        });
            }
        }.start();
    }

    private void startYhq() {

        synchronized (MainActivity.this) {
            if (mIsRunning) {
                return;
            }
            mIsRunning = true;
        }

        String tsStr = ((EditText) findViewById(R.id.time_etv)).getText().toString();
        mStartTs = getStartTs(tsStr);
        mIntervalMillis = Long.valueOf(((EditText)findViewById(R.id.interval_millis_etv)).getText().toString());
        mCnt = Integer.valueOf(((EditText)findViewById(R.id.cnt_etv)).getText().toString());
        mSumMaxCnt = Integer.valueOf(((EditText)findViewById(R.id.sum_etv)).getText().toString());

        mHandler.sendEmptyMessage(MSG_YHQ);

    }

    private void stopYhq() {
        synchronized (MainActivity.this) {
            mIsRunning = false;
        }
        mCnt = 0;
        mSumMaxCnt = 0;
        mCurrSumCnt = 0;
        mHandler.removeMessages(MSG_YHQ);
    }

    private long getStartTs(String s) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS", Locale.getDefault());
            Date parsedDate = dateFormat.parse(s);
            return parsedDate.getTime();
        } catch(Exception e) {
            return 0;
        }

    }
}
