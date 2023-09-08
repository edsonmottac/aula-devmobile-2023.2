package com.example.sqlopenhelper.dao;


import com.example.sqlopenhelper.model.Usuario;

import java.util.List;

public interface IUsuarioDAO {

    public boolean salvar (Usuario user);
    public boolean atualziar (Usuario user);
    public boolean deletar (Usuario user);
    public List<Usuario> listar();
}


