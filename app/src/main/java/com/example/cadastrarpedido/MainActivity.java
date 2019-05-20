package com.example.cadastrarpedido;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cadastrarpedido.BDHelper.Produtosdb;
import com.example.cadastrarpedido.model.Produtos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lista;
    Produtosdb bdHelper;
    ArrayList<Produtos> listview_produtos;
    Produtos produto;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_cadastrar = (Button) findViewById(R.id.btn_cadastrar);
        btn_cadastrar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Formulario_produtos.class);
                startActivity(intent);
            }
        });

        lista = (ListView) findViewById(R.id.listview_produtos);
        registerForContextMenu(lista);

        
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Produtos produtoEscolhido = (Produtos) adapter.getItemAtPosition(position);
                Intent i = new Intent (MainActivity.this, Formulario_produtos.class);
                i.putExtra("produto-escolhido", produtoEscolhido);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                produto = (Produtos) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar este produto");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                bdHelper = new Produtosdb(MainActivity.this);
                bdHelper.deletarProduto(produto);
                bdHelper.close();
                carregarProduto();
                return true;
            }
        });
    }

    protected void onResume(){
        super.onResume();
        carregarProduto();
    }

    public void carregarProduto(){
        bdHelper = new Produtosdb(MainActivity.this);
        listview_produtos = bdHelper.getLista();
        bdHelper.close();

        if(listview_produtos != null){
            adapter = new ArrayAdapter<Produtos>(MainActivity.this, android.R.layout.simple_list_item_1, listview_produtos);
            lista.setAdapter(adapter);

        }
        //finish();
    }


}
