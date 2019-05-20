package com.example.cadastrarpedido.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cadastrarpedido.model.Produtos;

import java.util.ArrayList;

public class Produtosdb extends SQLiteOpenHelper{
    //CRIANDO O BANCO
    private static final String DATABASE = "dbprodutos";
    private static final int VERSION = 1;
    //CONSTRUTOR DO BANCO
    public Produtosdb(Context context){
        super(context, DATABASE, null, VERSION);
    }

    @Override
    //QUERY PARA CRIAR A TABELA
    public void onCreate(SQLiteDatabase db) {
        String produto = "CREATE TABLE produtos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nomeproduto TEXT NOT NULL, descricao TEXT NOT NULL, quantidade INTEGER NOT NULL);";
        db.execSQL(produto); //EXECUTA A QUERY
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String produto = "DROP TABLE IF EXISTS produtos"; //exclui tabela se ja existir
        db.execSQL(produto);
    }

    public void salvarProduto(Produtos produto){
        ContentValues values = new ContentValues();

        //colocando o valor da variavel para dentro da coluna
        values.put("nomeproduto",produto.getNomeproduto());
        values.put("descricao",produto.getDescricao());
        values.put("quantidade",produto.getQuantidade());

        //insert
        getWritableDatabase().insert("produtos",null,values);
    }


    public void alterarProduto(Produtos produto){
        ContentValues values = new ContentValues();

        values.put("nomeproduto",produto.getNomeproduto());
        values.put("descricao",produto.getDescricao());
        values.put("quantidade",produto.getQuantidade());

        String[] args = {String.valueOf(produto.getId())};
        getWritableDatabase().update("produtos",values,"id=?",args);
    }


    public void deletarProduto(Produtos produto){

        String[] args = {String.valueOf(produto.getId())};
        getWritableDatabase().delete("produtos","id=?",args);

    }

    public ArrayList<Produtos> getLista(){
        String [] columns = {"id","nomeproduto","descricao","quantidade"};
        Cursor cursor = getWritableDatabase().query("produtos", columns, null, null, null, null,null, null);
        ArrayList<Produtos> produtos = new ArrayList<Produtos>();

        while (cursor.moveToNext()){ //CURSOR PARA PROXIMO REGISTRO
            Produtos produto = new Produtos();
            produto.setId(cursor.getLong(0));
            produto.setNomeproduto(cursor.getString(1));
            produto.setDescricao(cursor.getString(2));
            produto.setQuantidade(cursor.getInt(3));

            produtos.add(produto);
        }
        return produtos;
    }
}
