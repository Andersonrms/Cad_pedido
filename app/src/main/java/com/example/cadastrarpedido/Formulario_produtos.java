package com.example.cadastrarpedido;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cadastrarpedido.BDHelper.Produtosdb;
import com.example.cadastrarpedido.model.Produtos;
public class Formulario_produtos extends AppCompatActivity {

    EditText editText_nome, editText_descricao, editText_qtd;
    Button btn_modificar;
    Produtos editarProduto, produto;
    Produtosdb bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produtos);
        bdHelper = new Produtosdb (Formulario_produtos.this);
        Intent intent = getIntent();
        editarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");
        editText_nome = (EditText) findViewById(R.id.editText_nome);
        editText_descricao = (EditText) findViewById(R.id.editText_descricao);
        editText_qtd = (EditText) findViewById(R.id.editText_qtd);
        btn_modificar = (Button) findViewById(R.id.btn_modificar);

        if (editarProduto != null){
            btn_modificar.setText("Modificar");
        }else{
            btn_modificar.setText("Cadastrar");
        }

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produto.setNomeproduto(editText_nome.getText().toString());
                produto.setDescricao(editText_descricao.getText().toString());
                produto.setQuantidade(Integer.parseInt(editText_qtd.getText().toString()));

                if(btn_modificar.getText().toString().equals("Cadastrar")){
                    bdHelper.salvarProduto(produto);
                    bdHelper.close();
                }
            }
        });
    }
}
