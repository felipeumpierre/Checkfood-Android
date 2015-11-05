package com.checkfood.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.checkfood.asyncTask.Tarefa;
import com.checkfood.asyncTask.TarefaInterface;
import com.checkfood.bean.Board;
import org.json.JSONException;
import org.json.JSONObject;

import senac.checkfood.R;

public class TelaInicial extends ActionBarActivity implements TarefaInterface {

    public static final int REQUEST_CODE = 0;
    public Board board = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
    }

    public void callZXing(View view) {
        Intent it = new Intent(TelaInicial.this,com.google.zxing.client.android.CaptureActivity.class);
        startActivityForResult(it, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            board = new Board();
            String codeQr = data.getStringExtra("SCAN_RESULT");
            board.setId(Integer.parseInt(codeQr));
            autenticarMesa(board);
        }
    }

    public void autenticarMesa(Board board){
        Tarefa tarefa = new Tarefa(this,this);
        tarefa.execute("http://api-checkfood.rhcloud.com/api/board/"+board.getId()+"/","GET","");
    }

    @Override
    public void retornoWB(String string) {
        Board board = degenerateJson(string);
        if(board != null){
            Intent it = new Intent(TelaInicial.this,MenuPrincipal.class);
            Bundle params = new Bundle();
            params.putSerializable("board",board);
            it.putExtras(params);
            startActivity(it);
        }
        else{
            Context context = getApplicationContext();
            String mensagem = "Código Inválido";
            int duracao = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context,mensagem,duracao);
            toast.show();
        }
    }

    public Board degenerateJson(String data){

        board = new Board();

        try{
            if(data.contains("message")){
                return null;
            }
            else {
                JSONObject jo = new JSONObject(data);
                board.setId(jo.getInt("id"));
                board.setNumber(jo.getString("number"));
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return board;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
