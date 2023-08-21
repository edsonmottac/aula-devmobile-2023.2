package com.example.medidordeconsumo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReturnActivity extends AppCompatActivity {

    private TextView txtRetorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);

        txtRetorno = findViewById(R.id.txtRetorno);

        Bundle dados  = getIntent().getExtras();
        String marca =dados.getString("marca");
        String result = dados.getString("resultado");

        String msg = "O consumo atual do seu veículo " + marca + " e de " +  result + " km por litro";
        txtRetorno.setText(msg);
    }
}