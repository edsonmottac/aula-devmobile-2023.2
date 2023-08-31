package com.example.connectdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btConnect;
    private ListView lstPessoas;
    ArrayList<Pessoa> arlpessoas ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btConnect = findViewById(R.id.btConnect);

        //Listar Nomes
        lstPessoas = findViewById(R.id.lstPessoas);

        btConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Quado estamos acessando recursos de um banco de dados
                // precisa haver um tratamento de exceções
                try {

                    // Criar um banco de dados em modo privado
                    SQLiteDatabase database = openOrCreateDatabase("db_app", MODE_PRIVATE, null);

                    // Criar Tabela
                    database.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR, idade int(3))");

                    // deleta todas as pessoas
                    database.execSQL("DELETE FROM pessoas");

                    // Inserindo dados
                    database.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Adriana Soares', 25)");
                    database.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Marcos Costa', 45)");
                    database.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Marcelo Viana', 62)");

                    // Recuperar Daods
                    Cursor cursor = database.rawQuery("SELECT nome, idade FROM pessoas", null);

                    int idxNome = cursor.getColumnIndex("nome");
                    int idxIdade = cursor.getColumnIndex("idade");

                    arlpessoas = new ArrayList<>();

                    cursor.moveToFirst();
                    while (cursor != null) {
                        Log.i("RESULTADO - NOME ", cursor.getString(idxNome));
                        Log.i("RESULTADO - IDADE ", cursor.getString(idxIdade));

                        Pessoa p = new Pessoa();
                        p.setNome(cursor.getString(idxNome).toString());
                        p.setIdade(Integer.valueOf(cursor.getString(idxIdade)));
                        arlpessoas.add(p);

                        cursor.moveToNext();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ArrayAdapter<Pessoa> adaptador = new ArrayAdapter<>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        arlpessoas
                );
                lstPessoas.setAdapter(adaptador);
            }
        });
    }
}