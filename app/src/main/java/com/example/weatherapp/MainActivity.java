package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView res;
    String area;
    TextView desc;
    TextView temp;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title);
/*
        res = (TextView) findViewById(R.id.result);
        desc = (TextView) findViewById(R.id.description);
        temp = (TextView) findViewById(R.id.temp);
        spinner = (Spinner) findViewById(R.id.country_spinner);
        img = (ImageView) findViewById(R.id.img);


        Area area[] = new Area[6];

        area[0] = (new Area("اختر المنطقة", 0));
        area[1] = (new Area("عقابا", 97));
        area[2] = (new Area("تياسير", 98));
        area[3] = (new Area("بردلة", 269));
        area[4] = (new Area("طمون", 263));
        area[5] = (new Area("طوباس", 316));

        AreaAdapter adapter1 = new AreaAdapter(MainActivity.this, android.R.layout.simple_spinner_item, area);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(new SpinnerActivity());
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
/*
    class thread extends AsyncTask<URL, Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(URL... urls) {
            int y = 0;
            ArrayList<String> arrayList = new ArrayList<String>();
            URL url = urls[0];
            try { if(isNetworkConnected()) {
                URLConnection connection = url.openConnection();
                BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = bin.readLine()) != null) {
                    if (line.contains("اليوم")) {
                        y = 1;
                        line = bin.readLine();
                        String res = line.substring(line.indexOf("<h5>") + 4, line.indexOf("</h5>"));
                        arrayList.add(res);
                        Log.v("one :", res);

                    }//if
                    if (line.contains("<img class=\"weather-icon-img\"") && y == 1) {
                        line = line.substring(line.indexOf("src=") + 5, line.length() - 2);
                        arrayList.add(line);
                        Log.v("two :", line);

                        y = 0;
                    }//else
                    if (line.contains("<span class=\"weather-temp\">")) {
                        line = bin.readLine();
                        line = line.substring(0, line.indexOf("</span>"));
                        arrayList.add(line);
                        Log.v("three :", line);
                    }
                    if (line.contains("<span class=\" weather-status-desc\">")) {
                        line = line.substring(line.indexOf("desc\">") + 6, line.indexOf("</span>"));
                        arrayList.add(line);
                        Log.v("four :", line);
                    }

                }//while
            }
            else {Toast.makeText(MainActivity.this, "Check the internet connection", Toast.LENGTH_SHORT).show();}
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Check the internet connection", Toast.LENGTH_SHORT).show();
            }


            return arrayList;
        }


        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            if(isNetworkConnected()) {
                res.setText(strings.get(0));
                res.setTextColor(getResources().getColor(R.color.white));
                res.setTextSize(25);
                String name = strings.get(1);

                Picasso.with(MainActivity.this).load(name).
                        resize(300, 300).into(img);

                temp.setText(strings.get(2));
                desc.setText(strings.get(3));
            }
            else {
                Toast.makeText(MainActivity.this, "Check the internet connection", Toast.LENGTH_LONG).show();
            }

        }
    }//synck

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Area a = (Area) adapterView.getItemAtPosition(i);
            area = a.getName();
            if(!isNetworkConnected()){ Toast.makeText(MainActivity.this, "Check the internet connection", Toast.LENGTH_LONG).show();}
            else if(!area.equals("اختر المنطقة")) {
                try {
                    URL url = new URL("http://palweather.ps/temps/days/forecast/" + a.getId());
                    new thread().execute(url);
                    new thread1().execute(url);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Check the internet connection", Toast.LENGTH_LONG).show();
                }
            }//if

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(MainActivity.this, "Check the internet connection", Toast.LENGTH_LONG).show();
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }



    class thread1 extends AsyncTask<URL, Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(URL... urls) {

            ArrayList<String> arrayList = new ArrayList<String>();
            URL url = urls[0];
            int y=0;
            try { if(isNetworkConnected()) {
                URLConnection connection = url.openConnection();
                BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = bin.readLine()) != null) {
                    if (line.contains("ا<div class=\"weather-box\">")) {

                        line = bin.readLine();
                        String res = line.substring(line.indexOf("<h2>") + 4, line.indexOf("</h2>"));
                        arrayList.add(res);
                        Log.v("one1 :", res);

                    }//if
                    if (line.contains("<img class=\"weather-icon-img\"") ) {
                        line = line.substring(line.indexOf("src=") + 5, line.length() - 2);
                        arrayList.add(line);

                        Log.v("two1 :", line);

                    }//else
                    if (line.contains("<span class=\"weather-temp\">")) {
                        line = bin.readLine();
                        line = line.substring(0, line.indexOf("</span>"));
                        arrayList.add(line);
                        Log.v("three1 :", line);

                    }
                    if (line.contains("<span class=\" weather-status-desc\">")) {
                        line = line.substring(line.indexOf("desc\">") + 6, line.indexOf("</span>"));
                        arrayList.add(line);
                        Log.v("four1 :", line);
                    }
                    if(line.contains("<h2 class=\"day-title\">ليلاً</h2>")){
                        line = bin.readLine();
                        line = bin.readLine();
                        Log.v("res2 :", line);
                        String res2 = line.substring(line.indexOf("src=") + 5, line.indexOf("</span>")-2);

                        Log.v("res2 :", res2);
                        arrayList.add(res2);
                        line = bin.readLine();
                        line = bin.readLine();
                        String res = line.substring(0 , line.indexOf("</span>"));
                        Log.v("res :", res);
                        arrayList.add(res);
                        line = bin.readLine();
                        String res1 = line.substring(line.indexOf("\">")+2 , line.indexOf("</span>"));
                        Log.v("res1 :", res1);
                        arrayList.add(res1);
                    }

                }//while
            }
            else {Toast.makeText(MainActivity.this, "Check the internet connection", Toast.LENGTH_SHORT).show();}
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Check the internet connection", Toast.LENGTH_SHORT).show();
            }


            return arrayList;
        }


        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            if(isNetworkConnected()) {
                ArrayList<Word> list = new ArrayList<Word>();

                list.add(new Word(strings.get(3),"غــدا",strings.get(5),strings.get(4)));
                list.add(new Word(strings.get(6),"بـعد الـغد",strings.get(8),strings.get(7)));
                list.add(new Word(strings.get(9),"اليـــوم",strings.get(11),strings.get(10)));
                list.add(new Word(strings.get(12),"غــدا",strings.get(14),strings.get(13)));
                list.add(new Word(strings.get(15),"بـعد الـغد",strings.get(17),strings.get(16)));

                WordAdapter adapter = new WordAdapter (MainActivity.this, list);
                ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(adapter);


            }
            else {
                Toast.makeText(MainActivity.this, "Check the internet connection", Toast.LENGTH_LONG).show();
            }

        }
    }//synck
*/


}