package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void metodo1(View view) {
        TextView txt1 = findViewById(R.id.textView2);
        txt1.setText("Minha primeira frase");
    }

    public void metodo2(View view) {
        TextView txt1 = findViewById(R.id.textView2);
        txt1.setText("Minha segunda frase");
    }
}