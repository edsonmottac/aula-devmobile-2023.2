package com.example.sqlopenhelper.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlopenhelper.R;
import com.example.sqlopenhelper.dao.UsuarioDAO;
import com.example.sqlopenhelper.model.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editNome, editTelefone, editEndereco;
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

        lstUsuarios = findViewById(R.id.lstUsuarios);

        btSalvar = findViewById(R.id.btSalvar);
        btAlterar = findViewById(R.id.btAlterar);

        ListaUsuarios();

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsuarioDAO dbUser = new UsuarioDAO(getApplicationContext());
                Usuario user = new Usuario();
                user.setNome(editNome.getText().toString());
                user.setTelefone(editTelefone.getText().toString());
                user.setEndereco(editEndereco.getText().toString());

                dbUser.salvar(user);
                ListaUsuarios();
                limpacampos();

                Toast.makeText(MainActivity.this,
                        "Dados Salvos com Sucesso",
                        Toast.LENGTH_SHORT).show();

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

        lstUsuarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter<Usuario> adapter = (ArrayAdapter) lstUsuarios.getAdapter();
                Usuario selected = adapter.getItem(position);

                UsuarioDAO dbUser = new UsuarioDAO(getApplicationContext());
                Usuario user = new Usuario();
                user.setId((int) selected.getId());

                dbUser.deletar(user);
                limpacampos();
                ListaUsuarios();

                Toast.makeText(MainActivity.this,
                        "Registro Excluído com Sucesso",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    // Fora do OnCreate()
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

