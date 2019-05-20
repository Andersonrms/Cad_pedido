package com.example.cadastrarpedido.model;

import java.io.Serializable;

public class Produtos implements Serializable{
    private long id;
    private String nomeproduto, descricao;
    private int quantidade;


    @Override
    public String toString() {
        return nomeproduto.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeproduto() {
        return nomeproduto;
    }

    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produtos(){

    }





}
