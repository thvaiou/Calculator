package com.example.simplerssreader4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import com.example.simplerssreader4.parser.RSSFeed;
import com.example.simplerssreader4.parser.DOMParser;

public class SplashActivity extends Activity {

    private String RSSFEEDURL = "http://www.euroleague.net/rssfeed/1955/7364.xml";
    RSSFeed feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() == null
                    && !conMgr.getActiveNetworkInfo().isConnected()
                    && !conMgr.getActiveNetworkInfo().isAvailable()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Unable to reach server, \nPlease check your connectivity.")
                    .setTitle("RSS Reader").setCancelable(false).setPositiveButton("Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        } else {
            new AsyncLoadXMLFeed().execute();
        }
    }
    
    private class AsyncLoadXMLFeed extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            DOMParser myParser = new DOMParser();
            feed = myParser.parseXml(RSSFEEDURL);
            return null;
        }

        protected void onPostExecute(Void result) {
            super .onPostExecute(result);

            Bundle bundle = new Bundle();
            bundle.putSerializable("feed", feed);

            Intent intent = new Intent(SplashActivity.this, ListActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

            finish();
        }


    }
}
