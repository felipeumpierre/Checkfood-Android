package com.checkfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.checkfood.bean.Board;
import com.checkfood.bean.Ingredient;
import com.checkfood.bean.Product;
import com.checkfood.bean.RequestProduct;

import java.text.DecimalFormat;
import java.util.ArrayList;

import senac.checkfood.R;

public class ProductDeital extends ActionBarActivity {

    int position;
    Product product;
    Board board = new Board();
    public ArrayList<RequestProduct> requestProduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_deital);

        Intent it = getIntent();
        board = (Board) it.getSerializableExtra("board");
        requestProduct = (ArrayList<RequestProduct>)it.getSerializableExtra("requestProduct");
        product = (Product) it.getSerializableExtra("product");
        position =it.getIntExtra("position",0);

        DecimalFormat df = new DecimalFormat("##0.00");

        TextView deitalName = (TextView) findViewById(R.id.detailName);
        deitalName.setText(product.getName());

        TextView deitalDescription = (TextView) findViewById(R.id.deitalDescription);
        deitalDescription.setText("Categoria: "+ product.getCategory().getName());

        TextView deitalPrice = (TextView) findViewById(R.id.deitalPrice);
        deitalPrice.setText("Valor: R$ " + df.format(product.getPrice()));

        TextView ingredient = (TextView) findViewById(R.id.deitalIngredients);
        String ingredientStr = "";
        for (Ingredient i : product.getIngredients()) {
            ingredientStr += i.getName() + ", ";
        }
        ingredientStr = ingredientStr.length() > 0 ? ingredientStr.substring(0,ingredientStr.length() - 2) : ingredientStr;
        ingredient.setText(" Ingredientes: "+ingredientStr);

        ImageView image = (ImageView) findViewById(R.id.deitalImage);
        image.setImageResource(product.getImage(position));
    }


    public void buttonAdd(View view) {
        Intent it = new Intent(ProductDeital.this, ProductAdd.class);
        Bundle params = new Bundle();
        params.putSerializable("board",board);
        params.putSerializable("requestProduct",requestProduct);
        params.putSerializable("product",product);
        it.putExtras(params);
        startActivity(it);
    }
}
