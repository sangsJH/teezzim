package com.example.teezzim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.teezzim.db.AppDatabase;
import com.example.teezzim.db.Scorebord;
import com.example.teezzim.db.ScorebordDao;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private String url ="https://dev.mnemosyne.co.kr/teezzim/home?app=android";

    private CustomBridge customBridge;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customBridge = new CustomBridge();

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.addJavascriptInterface(new AndroidBridge(), "BRIDGE");
        mWebView.getSettings().setJavaScriptEnabled(true);

        //mWebView.loadUrl(url);

        String webUrlLocal = "file:///android_asset/index.html";
        mWebView.loadUrl(webUrlLocal);

        dbInit();

    }

    final class AndroidBridge {
        @JavascriptInterface //이게 있어야 웹에서 실행이 가능합니다.
        public void CallAndroid() {
            Toast.makeText(getApplicationContext(),"웹에서 클릭했어요",Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void Get_data(String str_json){
            Log.d("Json", str_json);
            Scorebord sbord = new Scorebord();
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(str_json);

                Log.d("Json Jobj", jsonObject.getString("date"));

                sbord.setDate(jsonObject.getString("date"));
                sbord.setTime(jsonObject.getString("time"));
                sbord.setLocation(jsonObject.getString("location"));
                boolean iscansel = false;
                sbord.setCansel(iscansel);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            new InsertAsyncTask(db.scorebordDao()).execute(sbord);
        }

        @JavascriptInterface
        public void Gallery(){
            Intent intent = new Intent(getApplicationContext(), Gallery.class);
            startActivity(intent);
        }

    }

    private void dbInit() {

        db = Room.databaseBuilder(this, AppDatabase.class, "Scorebord-db").build();

    }
    private static class InsertAsyncTask extends AsyncTask<Scorebord, Void, Void>{

        private ScorebordDao scorebordDao;
        public InsertAsyncTask(ScorebordDao scorebordDao){
            this.scorebordDao = scorebordDao;
        }

        @Override
        protected Void doInBackground(Scorebord... scorebords) {
            Log.d("doInBackground", scorebords[0].toString());
            Scorebord sbord = new Scorebord();
            sbord.setDate(scorebords[0].getDate());
            sbord.setTime(scorebords[0].getTime());
            sbord.setLocation(scorebords[0].getLocation());
            sbord.setCansel(scorebords[0].isCansel());
            this.scorebordDao.deleteall();
            this.scorebordDao.inset(sbord);

            return null;
        }
    }

}