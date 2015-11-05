package com.checkfood.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.checkfood.bean.Board;
import com.checkfood.bean.Product;
import com.checkfood.bean.RequestProduct;

import java.util.ArrayList;

import senac.checkfood.R;

public class ProductAdd extends ActionBarActivity {

    Board board = new Board();
    Product product = new Product();
    RequestProduct requestProduct = new RequestProduct();
    ArrayList<RequestProduct> requestProducts = new ArrayList<>();
    NumberPicker qtd =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_add);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        board = (Board) params.getSerializable("board");

        if(params.getSerializable("requestProduct") != null){
            requestProducts = (ArrayList<RequestProduct>)params.getSerializable("requestProduct");
        }
        product = (Product) params.getSerializable("product");

        qtd = (NumberPicker)findViewById(R.id.qtd);
        qtd.setMaxValue(10);
        qtd.setMinValue(1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addRequestProduct(View view){

        requestProduct = new RequestProduct();

        requestProduct.setProduct(product);

        NumberPicker quantity = (NumberPicker) findViewById(R.id.qtd);
        requestProduct.setQuantity(quantity.getValue());

        EditText observation = (EditText) findViewById(R.id.txtObservation);
        requestProduct.setObservation(observation.getText().toString());

        requestProducts.add(requestProduct);

        Intent it = new Intent(ProductAdd.this, ListarCardapio.class);
        Bundle params = new Bundle();
        params.putSerializable("board",board);
        params.putSerializable("requestProducts",requestProducts);
        it.putExtras(params);
        startActivity(it);
    }
}
