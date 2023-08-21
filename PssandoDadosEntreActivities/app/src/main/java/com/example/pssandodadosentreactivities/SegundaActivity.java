package com.example.pssandodadosentreactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    private TextView txtFilme, txtAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        txtFilme = findViewById(R.id.txtFilme);
        txtAno = findViewById(R.id.txtAno);

        Bundle dados  = getIntent().getExtras();
        String titulo =dados.getString("titulo");
        String ano = dados.getString("ano");

        txtFilme.setText(titulo);
        txtAno.setText(ano);

    }
}

