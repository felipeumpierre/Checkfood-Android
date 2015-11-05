package com.checkfood.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.checkfood.adapter.ProductRequestAdapter;
import com.checkfood.asyncTask.Tarefa;
import com.checkfood.asyncTask.TarefaInterface;
import com.checkfood.bean.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import senac.checkfood.R;

public class ResumoPedido extends ActionBarActivity implements TarefaInterface {

    Request request = new Request();
    DecimalFormat df = new DecimalFormat("##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumo_pedido);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        request = (Request) params.getSerializable("request");

        exibirPedido();
    }

    public void exibirPedido(){

        double total = 0;

        TextView selectedBoard = (TextView) findViewById(R.id.txtBoard);
        selectedBoard.setText("MESA " + request.getBoard().getNumber());

        ListView lv = (ListView) findViewById(R.id.listViewPratos);
        lv.setAdapter(new ProductRequestAdapter(this, request.getListRequestProduct()));

        for(int i=0; i<request.getListRequestProduct().size();i++){
            total = total + request.getListRequestProduct().get(i).getProduct().getPrice() * request.getListRequestProduct().get(i).getQuantity();
        }

        TextView totalPagar = (TextView) findViewById(R.id.txtTotalPagar);
        totalPagar.setText("Total a Pagar R$ " + df.format(total));
    }

    public void finalizarPedido(View view){
        String json = generateJson(request);
        Tarefa tarefa = new Tarefa(this,this);
        Log.i("Script","Url ->"+ "http://api-checkfood.rhcloud.com/api/order/board/"+request.getBoard().getId()+"/");
        tarefa.execute("http://api-checkfood.rhcloud.com/api/order/board/"+request.getBoard().getId()+"/", "POST", json);
    }

    public String generateJson(Request request){

        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        try{
            for(int i = 0; i<request.getListRequestProduct().size();i++) {
                jo = new JSONObject();
                jo.put("id", request.getListRequestProduct().get(i).getProduct().getId());
                jo.put("quantity", request.getListRequestProduct().get(i).getQuantity());
                jo.put("observation", request.getListRequestProduct().get(i).getObservation());
                ja.put(jo);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        Log.i("Script", "Json array pedido -> " + ja.toString());
        return ja.toString();
    }

    @Override
    public void retornoWB(String string) {
        Context context = getApplicationContext();
        String mensagem = "Pedido enviado com Sucesso!";
        int duracao = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,mensagem,duracao);
        toast.show();

        Intent it = new Intent(ResumoPedido.this,MenuPrincipal.class);
        Bundle params = new Bundle();
        params.putSerializable("board",request.getBoard());
        it.putExtras(params);
        startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resumo_pedido, menu);
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
