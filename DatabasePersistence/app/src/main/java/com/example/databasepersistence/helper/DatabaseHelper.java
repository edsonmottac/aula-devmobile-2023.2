package com.example.databasepersistence.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Chamada apenas uma vez (após a primeira instalação do app)

        try {

            db.execSQL("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(255), telefone VARCHAR(255), endereco VARCHAR(255))");

            Log.i("INFO BD", "Tabelas criadas com sucesso");

        } catch (Exception ex) {
            Log.i("INFO BD", "Erro na criação das tabelas   " + ex.getMessage());
        }

    }

    // Esse metodo reconehce a versão, portanto sabe se é para atualizar ou para modificar
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            db.execSQL("DROP TABLE IF EXISTS usuarios");
            Log.i("INFO BD", "Tabelas deletadas com sucesso");

        } catch (Exception ex) {
            Log.i("INFO BD", "Erro na deleção das tabelas " + ex.getMessage());
        }
    }
}
