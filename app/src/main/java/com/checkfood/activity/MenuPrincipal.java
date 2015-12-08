package com.checkfood.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.checkfood.bean.Board;

import senac.checkfood.R;

public class MenuPrincipal extends ActionBarActivity {

    Board board = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        Intent it = getIntent();

        //if(it.getSerializableExtra("board") != null){
            Bundle params = it.getExtras();
            board = (Board)params.getSerializable("board");
        //}
    }

    public void listarCardapio(View view){
        Intent it = new Intent(MenuPrincipal.this, ListarCardapio.class);
        Bundle params = new Bundle();
        params.putSerializable("board",board);
        it.putExtras(params);
        startActivity(it);
    }

    public void visualizarPedido(View view){

    }

    public void rateRestaurant(View view){
        Intent it = new Intent(MenuPrincipal.this, RateRestaurant.class);
        Bundle params = new Bundle();
        params.putSerializable("board",board);
        it.putExtras(params);
        startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
}
