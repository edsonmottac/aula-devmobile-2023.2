package com.example.databasepersistence.model;

import com.example.databasepersistence.usuario.Usuario;

import java.util.List;

public interface IUsuarioDAO {

    public boolean salvar (Usuario user);
    public boolean atualziar (Usuario user);
    public boolean deletar (Usuario user);
    public List<Usuario> listar();


}
