package com.example.databasepersistence.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.databasepersistence.helper.DatabaseHelper;
import com.example.databasepersistence.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;



    // Acessar o DBHolper
    public UsuarioDAO(Context ctx) {
        DatabaseHelper db = new DatabaseHelper(ctx);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Usuario user) {

        try {

            ContentValues cv = new ContentValues();
            cv.put("nome",user.getNome());
            cv.put("telefone",user.getTelefone());
            cv.put("endereco",user.getEndereco());

            escreve.insert("usuarios", null,cv);

            Log.i("INFO BD", "Dados Salvos com Sucesso");

        } catch (Exception ex) {
            Log.i("INFO BD", "Falha na gravação dos dados");
            return false;
        }
        return true;
    }

    @Override
    public boolean atualziar(Usuario user) {
        try {

            ContentValues cv = new ContentValues();
            cv.put("nome",user.getNome());
            cv.put("telefone",user.getTelefone());
            cv.put("endereco",user.getEndereco());

            String[] args ={String.valueOf(user.getId())};
            escreve.update("usuarios", cv, "id = ?",  args);

            Log.i("INFO BD", "Dados Salvos com Sucesso");

        } catch (Exception ex) {
            Log.i("INFO BD", "Falha na gravação dos dados");
            return false;
        }
        return true;

    }

    @Override
    public boolean deletar(Usuario user) {


        try {
            escreve.delete("usuarios", "id = " + user.getId(), null);
            Log.i("INFO BD", "Dados Deletados com Sucesso");

        }catch (Exception ex) {
            Log.i("INFO BD", "Falha oa deletar os dados " + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Usuario> listar() {

        List<Usuario> lista_usuarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM usuarios ";
            Cursor cursor = le.rawQuery(sql, null);

            int iid = cursor.getColumnIndexOrThrow("id");
            int inome = cursor.getColumnIndexOrThrow("nome");
            int itelefone = cursor.getColumnIndexOrThrow("nome");
            int iendereco = cursor.getColumnIndexOrThrow("endereco");

            cursor.moveToFirst();
           do {

                Usuario user = new Usuario();
                user.setId(Integer.valueOf(cursor.getString(iid)));
                user.setNome(cursor.getString(inome));
                user.setTelefone(cursor.getString(itelefone));
                user.setEndereco(cursor.getString(iendereco));
                lista_usuarios.add(user);

                Log.i("INFO BD", "LOOP");

           } while (cursor.moveToNext());


            Log.i("INFO BD", "Sucesso na listagem dos dados");
        } catch (Exception ex) {
            Log.i("INFO BD", "Falha na listagem dos dados: " + ex.getMessage());
            return null;
        }

        return lista_usuarios;
    }
}
