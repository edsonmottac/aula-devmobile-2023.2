package com.example.sqlopenhelper.model;


public class Usuario {
    private int id;
    private String nome, telefone, endereco;
    public String getNome() {return nome;}
    public int getId() {return id; }
    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public String getEndereco() {return endereco;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    @Override
    public String toString(){
        return nome;
    }
}


