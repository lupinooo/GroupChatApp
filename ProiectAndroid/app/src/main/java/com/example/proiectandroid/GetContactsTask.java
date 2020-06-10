package com.example.proiectandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.util.Xml;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public  class GetContactsTask extends AsyncTask<URL, Void, String[]> {
    public static String sbuf = "";
    public static int userNo;
    InputStream ist = null;


    @Override
    protected String[] doInBackground(URL... urls) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) urls[0].openConnection();
            conn.setRequestMethod("GET");
            ist = conn.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(ist));
            String line = "";
            while ((line = br.readLine()) != null) {
                sbuf += line;
            }

            JSONObject countriesTotal=new JSONObject(sbuf);
            JSONArray countriesArray=countriesTotal.getJSONArray("countries");
            String[] result=new String[countriesArray.length()];
            for (int i = 0; i < countriesArray.length(); i++) {
                JSONObject user = countriesArray.getJSONObject(i);
                String name=user.getString("name");
                String code=user.getString("code");
                result[i] = code+": "+name;
            }
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    }







