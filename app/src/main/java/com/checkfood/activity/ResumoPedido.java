package com.checkfood.activity;

<<<<<<< HEAD
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
=======
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
<<<<<<< HEAD
import android.widget.AdapterView;
=======
>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
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

<<<<<<< HEAD
    private static String TAG = "LOG";
    private Toolbar mToolbar;

    Request request = new Request();
    DecimalFormat df = new DecimalFormat("##0.00");
    double total = 0;
=======
    Request request = new Request();
    DecimalFormat df = new DecimalFormat("##0.00");
>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumo_pedido);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        request = (Request) params.getSerializable("request");

<<<<<<< HEAD
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Pedido Mesa "+request.getBoard().getNumber());
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        mToolbar.setLogo(R.drawable.checkbox_marked_outline);
        setSupportActionBar(mToolbar);

=======
>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
        exibirPedido();
    }

    public void exibirPedido(){

<<<<<<< HEAD
        final ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new ProductRequestAdapter(this, request.getListRequestProduct()));

        totalizarValor();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ResumoPedido.this);
                alertDialogBuilder.setTitle("Atenção");
                alertDialogBuilder.setMessage("Deseja excluir o item "+ request.getListRequestProduct().get(position).getProduct().getName()+"?").setCancelable(false)
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            exibirPedido();
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(ResumoPedido.this, "Prato excluído: " + request.getListRequestProduct().get(position).getProduct().getName(), Toast.LENGTH_LONG).show();
                            request.getListRequestProduct().remove(position);
                            exibirPedido();
                            dialog.cancel();
                        }
                    });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    public void totalizarValor(){
        total = 0;
        for(int i=0; i<request.getListRequestProduct().size();i++){
            total = total + request.getListRequestProduct().get(i).getProduct().getPrice() * request.getListRequestProduct().get(i).getQuantity();
        }
=======
        double total = 0;

        TextView selectedBoard = (TextView) findViewById(R.id.txtBoard);
        selectedBoard.setText("MESA " + request.getBoard().getNumber());

        ListView lv = (ListView) findViewById(R.id.listViewPratos);
        lv.setAdapter(new ProductRequestAdapter(this, request.getListRequestProduct()));

        for(int i=0; i<request.getListRequestProduct().size();i++){
            total = total + request.getListRequestProduct().get(i).getProduct().getPrice() * request.getListRequestProduct().get(i).getQuantity();
        }

>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
        TextView totalPagar = (TextView) findViewById(R.id.txtTotalPagar);
        totalPagar.setText("Total a Pagar R$ " + df.format(total));
    }

    public void finalizarPedido(View view){
        String json = generateJson(request);
        Tarefa tarefa = new Tarefa(this,this);
<<<<<<< HEAD
        tarefa.execute("http://api-checkfood.rhcloud.com/api/order/board/"+request.getBoard().getId(),"", json);
=======
        Log.i("Script","Url ->"+ "http://api-checkfood.rhcloud.com/api/order/board/"+request.getBoard().getId()+"/");
        tarefa.execute("http://api-checkfood.rhcloud.com/api/order/board/"+request.getBoard().getId()+"/", "POST", json);
>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
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
<<<<<<< HEAD
=======
        Log.i("Script", "Json array pedido -> " + ja.toString());
>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
        return ja.toString();
    }

    @Override
    public void retornoWB(String string) {
<<<<<<< HEAD

        Log.i("Script","Retorno json-> "+string);

=======
>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
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
<<<<<<< HEAD
        int id = item.getItemId();

        if(id == R.id.menu){

        }
        if(id == R.id.excluir){

        }
        if(id == R.id.sair){

        }

=======
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
        return super.onOptionsItemSelected(item);
    }
}
