package com.example.myasynctaskapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Button btGetData;
    private TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btGetData = findViewById(R.id.btGetData);
        txtResult = findViewById(R.id.txtResult);


        btGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyTask task = new MyTask();
                String uriAPI = "https://viacep.com.br/ws/01001000/json";
                task.execute(uriAPI);


               // Toast.makeText(MainActivity.this, "Consumindo API Via-CEP", Toast.LENGTH_SHORT).show();

            }
        });


    }


    class MyTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            String StringUrl = strings[0];
            InputStream inputstream = null;
            InputStreamReader inputStreamReader;
            BufferedReader reader = null;
            StringBuffer buffer;

            try {

                URL url = new URL(StringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                inputstream = conexao.getInputStream();
                inputStreamReader = new InputStreamReader(inputstream);

                reader = new BufferedReader(inputStreamReader);

                buffer = new StringBuffer();
                String linha;
                while ((linha = reader.readLine()) != null) {

                    buffer.append(linha);

                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String cep, logradouro, complemento, bairro;


            try {

                /*
                // inserindo e, variáveis
                JSONObject json = new JSONObject(s);
                cep = json.getString("cep");
                logradouro = json.getString("logradouro");
                complemento = json.getString("complemento");
                bairro = json.getString("bairro");
                txtResult.setText(cep + " | " + logradouro);
                 */


                //Inserindo em objetos - preisa da referêncai no gradle: implementation("com.google.code.gson:gson:2.8.8")
                JSONObject json = new JSONObject(s);
                Gson gson = new Gson();
                CEPModel cepModel = gson.fromJson(String.valueOf(json), CEPModel.class);
                txtResult.setText(cepModel.getBairro());



            } catch (Exception ex) {
                throw new  RuntimeException(ex);
            }









        }
    }




}