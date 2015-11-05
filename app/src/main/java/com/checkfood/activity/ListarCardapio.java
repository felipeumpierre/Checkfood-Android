package com.checkfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.checkfood.adapter.ProductAdapter;
import com.checkfood.asyncTask.Tarefa;
import com.checkfood.asyncTask.TarefaInterface;
import com.checkfood.bean.Board;
import com.checkfood.bean.Category;
import com.checkfood.bean.Ingredient;
import com.checkfood.bean.Product;
import com.checkfood.bean.Request;
import com.checkfood.bean.RequestProduct;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;

import senac.checkfood.R;

public class ListarCardapio extends ActionBarActivity  implements TarefaInterface {

    Board board = new Board();
    public ArrayList<Product> listProducts = new ArrayList<>();
    public Request request = new Request();
    public ArrayList<RequestProduct> requestProduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_cardapio);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        board = (Board) params.getSerializable("board");
        requestProduct = (ArrayList<RequestProduct>) params.getSerializable("requestProducts");

        getJson();
    }

    public void getJson() {
        carregarProdutos("http://api-checkfood.rhcloud.com/api/menu/", "GET", "");
    }

    public void carregarProdutos(final String url, final String method, final String data){
        Tarefa tarefa = new Tarefa(this,this);
        tarefa.execute(url, method, data);
    }

    @Override
    public void retornoWB(String answer) {
        degenerateJson(answer);
        exibirProdutos();
    }

    public void degenerateJson (String data) {
        try{
            Product product;
            Ingredient ingredient;
            Category category;
            JSONArray ja = new JSONArray(data);

            for(int i = 0 ; i < ja.length(); i++){

                product = new Product();
                category = new Category();
                ArrayList<Ingredient> ingredients = new ArrayList<>();

                product.setId(ja.getJSONObject(i).getInt("id"));
                product.setName(ja.getJSONObject(i).getString("name"));
                product.setPrice(ja.getJSONObject(i).getDouble("price"));
                product.setDescription(ja.getJSONObject(i).getString("description"));

                category.setId(ja.getJSONObject(i).getJSONObject("category").getInt("id"));
                category.setName(ja.getJSONObject(i).getJSONObject("category").getString("name"));

                product.setCategory(category);

                for (int x = 0; x<ja.getJSONObject(i).getJSONArray("ingredients").length(); x++) {
                    ingredient = new Ingredient();
                    ingredient.setId(ja.getJSONObject(i).getJSONArray("ingredients").getJSONObject(x).getInt("id"));
                    ingredient.setName(ja.getJSONObject(i).getJSONArray("ingredients").getJSONObject(x).getString("name"));
                    ingredients.add(x,ingredient);
                }
                product.setIngredients(ingredients);

                listProducts.add(i,product);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void exibirProdutos(){
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new ProductAdapter(this, listProducts));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProductDeital.class);
                Bundle params = new Bundle();
                params.putSerializable("board",board);
                params.putSerializable("requestProduct",requestProduct);
                params.putSerializable("product",listProducts.get(position));
                params.putSerializable("position", position);
                intent.putExtras(params);
                startActivity(intent);
            }

        });
    }

    public void prosseguirPedido(View view){

        request.setBoard(board);
        request.setListRequestProduct(requestProduct);

        Intent it = new Intent(ListarCardapio.this, ResumoPedido.class);
        Bundle params = new Bundle();
        params.putSerializable("request",request);
        it.putExtras(params);
        startActivity(it);
    }
}
