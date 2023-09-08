package com.example.sqlopenhelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqlopenhelper.helper.SQLiteDatabaseHelper;
import com.example.sqlopenhelper.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {

    private SQLiteDatabase objWrite;
    private SQLiteDatabase objRead;

    public UsuarioDAO(Context ctx) {
        SQLiteDatabaseHelper db = new SQLiteDatabaseHelper(ctx);
        objWrite = db.getWritableDatabase();
        objRead = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Usuario user) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("nome",user.getNome());
            cv.put("telefone",user.getTelefone().toString());
            cv.put("endereco",user.getEndereco().toString());

            objWrite.insert("usuarios", null,cv);

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
            objWrite.update("usuarios", cv, "id = ?",  args);

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
            objWrite.delete("usuarios", "id = " + user.getId(), null);

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
            Cursor cursor = objRead.rawQuery(sql, null);

            int iid = cursor.getColumnIndexOrThrow("id");
            int inome = cursor.getColumnIndexOrThrow("nome");
            int itelefone = cursor.getColumnIndexOrThrow("telefone");
            int iendereco = cursor.getColumnIndexOrThrow("endereco");

            cursor.moveToFirst();

            do {
                if (cursor.getCount() ==0) {break;}
                Usuario user = new Usuario();
                user.setId(Integer.valueOf(cursor.getString(iid)));
                user.setNome(cursor.getString(inome));
                user.setTelefone(cursor.getString(itelefone));
                user.setEndereco(cursor.getString(iendereco));
                lista_usuarios.add(user);
            } while (cursor.moveToNext());

            Log.i("INFO BD", "Sucesso na listagem dos dados");
        } catch (Exception ex) {
            Log.i("INFO BD", "Falha na listagem dos dados: " + ex.getMessage());
            return null;
        }
        return lista_usuarios;
    }
}



