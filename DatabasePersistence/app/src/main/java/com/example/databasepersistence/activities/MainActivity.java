package com.example.databasepersistence.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.databasepersistence.R;
import com.example.databasepersistence.helper.DatabaseHelper;
import com.example.databasepersistence.model.UsuarioDAO;
import com.example.databasepersistence.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editTelefone;
    private EditText editEndereco;
    private Button btSalvar;
    private Button btAlterar;

    private ListView lstUsuarios;

    private int idTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = findViewById(R.id.editNome);
        editTelefone = findViewById(R.id.editTelefone);
        editEndereco = findViewById(R.id.editEndereco);

        btSalvar = findViewById(R.id.btSalvar);
        btAlterar = findViewById(R.id.btAlterar);
        lstUsuarios = findViewById(R.id.lstUsuarios);

        //ListaUsuarios();


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Inserindo dados
                /*
                DatabaseHelper database = new DatabaseHelper(getApplicationContext());
                ContentValues cv = new ContentValues();
                cv.put("nome", "Edson");
                cv.put("telefone", "1234567");
                cv.put("endereco", "Rua A");
                database.getReadableDatabase().insert("usuarios", null, cv);
                 */

                UsuarioDAO dbUser = new UsuarioDAO(getApplicationContext());
                Usuario user = new Usuario();
                user.setNome(editNome.getText().toString());
                user.setTelefone(editTelefone.getText().toString());
                user.setEndereco(editEndereco.getText().toString());

                dbUser.salvar(user);
                limpacampos();
                ListaUsuarios();


            }
        });

        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsuarioDAO dbUser = new UsuarioDAO(getApplicationContext());
                Usuario user = new Usuario();
                user.setId(idTemp);
                user.setNome(editNome.getText().toString());
                user.setTelefone(editTelefone.getText().toString());
                user.setEndereco(editEndereco.getText().toString());

                dbUser.atualziar(user);
                limpacampos();
                ListaUsuarios();

            }
        });


        lstUsuarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter<Usuario> adapter = (ArrayAdapter) lstUsuarios.getAdapter();
                Usuario selected = adapter.getItem(position);

                Log.i("INFO BD", "Selecionado: " + selected.getId());

                UsuarioDAO dbUser = new UsuarioDAO(getApplicationContext());
                Usuario user = new Usuario();
                user.setId((int) selected.getId());

                dbUser.deletar(user);
                ListaUsuarios();
                limpacampos();

                return true;

            }
        });


        lstUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter<Usuario> adapter = (ArrayAdapter) lstUsuarios.getAdapter();
                Usuario selected = adapter.getItem(position);
                idTemp=selected.getId();
                editNome.setText(selected.getNome());
                editTelefone.setText(selected.getTelefone());
                editEndereco.setText(selected.getEndereco());

            }
        });


    }

    public void ListaUsuarios(){

        UsuarioDAO dbUser = new UsuarioDAO(getApplicationContext());
        List<Usuario> lista =  dbUser.listar();

        ArrayAdapter<Usuario> adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                lista
        );
        lstUsuarios.setAdapter(adaptador);

    }

    public void limpacampos(){
        editNome.setText("");
        editTelefone.setText("");
        editEndereco.setText("");
    }

}