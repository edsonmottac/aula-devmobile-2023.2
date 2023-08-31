package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btSalvar;
    private EditText EditNome;
    private TextView txtPreferencia;
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSalvar = findViewById(R.id.btSalvar);
        EditNome = findViewById(R.id.editNome);
        txtPreferencia = findViewById(R.id.txtPreferencia);


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Cria o arquivo de preferêncais

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
                SharedPreferences.Editor editor = preferences.edit();

                //Salva as informçaoes
                if (EditNome.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha o Nome", Toast.LENGTH_LONG).show();
                } else {
                    String nome = EditNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    txtPreferencia.setText("Olá " + nome);
                }

            }
        });


        // Carregar as preferêncas
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);

        // testa se a preferencia com a chave "nome" existe
        if (preferences.contains("nome")) {

            String nome = preferences.getString("nome", "Sem preferências armazenadas");
            txtPreferencia.setText("Olá " + nome);


        } else {
            txtPreferencia.setText("Sem preferências armazenadas");
        }

    }
}