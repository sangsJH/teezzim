package com.example.teezzim;


import android.util.Log;
import android.webkit.JavascriptInterface;

class CustomBridge {
    @JavascriptInterface
    public void Call_log(String msg){
        Log.i(getClass().getName(), "Call_log() Msg" + msg);
    }

}
