package com.checkfood.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.checkfood.asyncTask.Tarefa;
import com.checkfood.asyncTask.TarefaInterface;
import com.checkfood.bean.Board;
import com.checkfood.bean.Rate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import senac.checkfood.R;

public class RateRestaurant extends ActionBarActivity implements TarefaInterface {

    private static String TAG = "LOG";
    private Toolbar mToolbar;

    Board board = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_restaurant);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        board = (Board) params.getSerializable("board");

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Avalie o Local");
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        mToolbar.setLogo(R.drawable.star);
        setSupportActionBar(mToolbar);
    }

    public void submit(View view){

        RatingBar grade = (RatingBar) findViewById(R.id.ratingBarGrade);
        EditText opinion = (EditText) findViewById(R.id.txtOpinion);

        Rate rate = new Rate();
        rate.setGrade(grade.getRating());
        rate.setOpinion(opinion.getText().toString());

        String json = generateJson(rate);
        Log.i("Script","Teste json-> "+json);
        submitWS(json);
    }

    public String generateJson(Rate rate){

        String json="";
        JSONObject jo = new JSONObject();

        try {
            jo.put("grade", rate.getGrade());
            jo.put("opinion", rate.getOpinion());
            json = jo.toString();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public void submitWS(String json){
        Tarefa tarefa = new Tarefa(this,this);
        tarefa.execute("http://api-checkfood.rhcloud.com/api/opinion","",json);
    }

    @Override
    public void retornoWB(String string) {

        Log.i("Script","Retorno json-> "+string);

        Context context = getApplicationContext();
        String mensagem = "Avaliação enviada com Sucesso!";
        int duracao = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,mensagem,duracao);
        toast.show();

        Intent it = new Intent(RateRestaurant.this,MenuPrincipal.class);
        Bundle params = new Bundle();
        params.putSerializable("board",board);
        it.putExtras(params);
        startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rate_restaurant, menu);
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
