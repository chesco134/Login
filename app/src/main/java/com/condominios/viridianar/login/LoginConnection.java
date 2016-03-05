package com.condominios.viridianar.login;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Viridiana R on 17/02/2016.
 */
public class LoginConnection extends AsyncTask<String,String,String> {
    String user, password;
    Context context;
    public LoginConnection(Context context, String user, String password){
        this.context = context;
        this.user = user;
        this.password = password;
    }
    @Override
    protected String doInBackground(String... params){
        try {
            URL url = new URL("http://192.168.1.65:8080/Condominios/WS"); // la url del ws
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setDoOutput(true);//to upload data
            //urlCon.setRequestMethod("POST");
            //to send
            JSONObject json = new JSONObject();
            json.put("user", user);
            json.put("password", password);
            DataOutputStream salida = new DataOutputStream(urlCon.getOutputStream());
            salida.write(("data=" + json.toString()).getBytes());
            salida.flush();

            //to receive
            DataInputStream entrada = new DataInputStream(urlCon.getInputStream());
            int length;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] chunk = new byte[128];
            while( (length = entrada.read(chunk)) != -1)
                baos.write(chunk,0,length);
            //json = new JSONObject(baos.toString());
            Log.d("MACHA", baos.toString());
            return baos.toString();
        }catch(JSONException | IOException e){
            Log.d("MACHA", e.toString());
            return "Error: " + e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
