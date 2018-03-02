package com.spoorthi.blockcallsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

/**
 * Created by Spoorthi on 2/8/2018.
 */

public class BlockCallReceiver extends BroadcastReceiver
{
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle myBundle = intent.getExtras();
        if (myBundle != null)
        {
            System.out.println("--------Not null-----");
            try
            {
                if (intent.getAction().equals("android.intent.action.PHONE_STATE"))
                {
                    String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                    System.out.println("--------in state-----");
                    if (state.equals(TelephonyManager.EXTRA_STATE_RINGING))
                    {
                        // Incoming call
                        String incomingNumber =intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                        System.out.println("--------------my number---------"+incomingNumber);

                        // this is main section of the code,. could also be use for particular number.
                        // Get the boring old TelephonyManager.
                        TelephonyManager telephonyManager =(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

                        // Get the getITelephony() method
                         Class<?> classTelephony = Class.forName(telephonyManager.getClass().getName());
                        Method methodGetITelephony = classTelephony.getDeclaredMethod("getITelephony");

                        // Ignore that the method is supposed to be private
                         methodGetITelephony.setAccessible(true);

                        // Invoke getITelephony() to get the ITelephony interface
                        Object telephonyInterface = methodGetITelephony.invoke(telephonyManager);

                        // Get the endCall method from ITelephony
                        Class<?> telephonyInterfaceClass = Class.forName(telephonyInterface.getClass().getName());
                        Method methodEndCall = telephonyInterfaceClass.getDeclaredMethod("endCall");

                        // Invoke endCall()
                        methodEndCall.invoke(telephonyInterface);

                    }

                }
            }
            catch (Exception ex)
            { // Many things can go wrong with reflection calls
                ex.printStackTrace();
            }
        }
    }
}
