//package com.spoorthi.blockcallsample;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.telephony.TelephonyManager;
//import android.util.Log;
//
//import com.android.internal.telephony.ITelephony;
//
//import java.lang.reflect.Method;
//
///**
// * Created by Spoorthi on 2/7/2018.
// */
//
//public class BlockCall extends BroadcastReceiver
//{
//    Context context = null;
//    private static final String TAG = "Phone call";
//    private ITelephony telephonyService;
//
//    @Override
//    public void onReceive(Context context, Intent intent)
//    {
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        try {
//            Class c = Class.forName(tm.getClass().getName());
//            Method m = c.getDeclaredMethod("getITelephony");
//            m.setAccessible(true);
//            telephonyService = (ITelephony) m.invoke(tm);
//            Bundle bundle = intent.getExtras();
//            String phoneNumber = bundle.getString("incoming_number");
//            Log.d("INCOMING", phoneNumber);
//            if ((phoneNumber != null)) {
//                telephonyService.endCall();
//                Log.d("HANG UP", phoneNumber);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
