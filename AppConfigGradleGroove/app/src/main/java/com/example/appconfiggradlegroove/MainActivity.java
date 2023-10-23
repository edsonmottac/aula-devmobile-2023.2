package com.example.appconfiggradlegroove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lstView;

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    private List<Usuario> list_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstView = findViewById(R.id.lstView);

        lstView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter<Usuario> adapter = (ArrayAdapter<Usuario>) parent.getAdapter();
                Usuario user = adapter.getItem(position);

                reference.child("Usuario").child(user.getId()).removeValue();

                Toast.makeText(MainActivity.this, "O Id: " + user.getId() + " foi deletado com sucesso!", Toast.LENGTH_SHORT).show();

                return false;
            }
        });




        // Grava entrada no Firebase
        reference.child("Permissões").setValue("MINHA CHAVE");


        /*
        // Criando estruturas de objetos no Firebase
        reference.child("Usuario").child("004").child("nome").setValue("Fulano de Tal");
        reference.child("Usuario").child("004").child("login").setValue("Fulano");
        reference.child("Usuario").child("004").child("senha").setValue("1234");
         */


        // Passando objetos para o firebase
        //reference.child("Usuario").child("003").removeValue();

        Usuario user = new Usuario();
        user.setNome("Edson Mota");
        user.setLogin("edson");
        user.setSenha("12345");
        reference.child("Usuario").child("005").setValue(user);

        // recuperando dados do Firebase - Listener
        DatabaseReference UserReference = reference.child("Usuario");

        list_usuario = new ArrayList<>();

        UserReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list_usuario.clear();
                for (DataSnapshot current_user: snapshot.getChildren()) {

                    Usuario u = new Usuario();
                    u.setId(current_user.getKey().toString());
                    u.setNome(current_user.child("nome").getValue().toString());
                    u.setLogin(current_user.child("login").getValue().toString());
                    u.setSenha(current_user.child("senha").getValue().toString());
                    list_usuario.add(u);
                    Log.i("MyFirebase", "Meu id: " + u.getId() );
                }
                UpdateList();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void UpdateList() {
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<>(
                getApplication(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                list_usuario
        );
        lstView.setAdapter(adapter);
    }
}