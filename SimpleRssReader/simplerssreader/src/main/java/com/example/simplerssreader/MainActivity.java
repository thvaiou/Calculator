package com.example.simplerssreader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

    private PostData[] listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        this.generateDummyData();

        ListView listView = (ListView) this.findViewById(R.id.postListView);

        PostItemAdapter itemAdapter = new PostItemAdapter(this, R.layout.postitem, listData);
        listView.setAdapter(itemAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void generateDummyData() {
        PostData data = null;
        listData = new PostData[10];
        for (int i = 0; i < 10; i++) {
            data = new PostData();
            data.postDate = "May 20, 2013";
            data.postTitle = "Post" + (i + 1) + " Title: This is the Post title from RSS Feed";
            data.postThumbUrl = null;
            listData[i] = data;
        }
    }

    private class RssDataController extends AsyncTask<String, Integer, PostData[]> {
        private RSSXMLTag currentTag;
        @Override
        protected PostData[] doInBackground(String... params) {
            String urlStr = params[0];
            InputStream is = null;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10*1000);
                connection.setConnectTimeout(10*1000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                int response = connection.getResponseCode();
                Log.d("debug", "The response is: " + response);
                is = connection.getInputStream();

                final int bufferSize = 1024;
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                while (true) {
                    int count = is.read(buffer, 0, bufferSize);
                    if (count == -1) {
                        break;
                    }

                    os.write(buffer);
                }
                os.close();

                String result = new String(os.toByteArray(), "UTF-8");
                Log.d("debug", result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
