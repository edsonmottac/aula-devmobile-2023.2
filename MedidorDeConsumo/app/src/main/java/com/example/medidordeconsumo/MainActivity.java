package com.example.medidordeconsumo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtMarca;
    private EditText txtDistancia;
    private EditText txtConsumo;
    private Button btCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMarca = findViewById(R.id.txtMarca);
        txtDistancia = findViewById(R.id.txtDistancia);
        txtConsumo = findViewById(R.id.txtConsumo);
        btCalcular = findViewById(R.id.btCalcular);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float dist =  Float.parseFloat(txtDistancia.getText().toString());
                float consumo = Float.parseFloat(txtConsumo.getText().toString());
                float result = dist/consumo;

                Intent intent = new Intent(getApplicationContext(), ReturnActivity.class);

                intent.putExtra("marca",txtMarca.getText().toString());
                intent.putExtra("resultado",String.valueOf(result));

                startActivity(intent);
            }
        });
    }
}