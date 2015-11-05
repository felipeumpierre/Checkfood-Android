package com.checkfood.asyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.checkfood.http.HttpConnection;

public class Tarefa extends AsyncTask<String,String,String> {

    private Context context;
    private TarefaInterface ti;
    private ProgressDialog progress;

    public Tarefa(Context context, TarefaInterface ti) {
        this.context = context;
        this.ti = ti;
    }

    @Override
    protected  void onPreExecute(){
        progress = new ProgressDialog(context);
        progress.setMessage("Carregando...");
        progress.show();
    }

    @Override
    protected String doInBackground(final String... params) {
        try {
        String answer = HttpConnection.getSetDataWeb(params[0],params[1],params[2]);
        return answer;
        } catch (Exception e) {
        Log.e("Erro", "Falha ao acessar Web service", e);
        return null;}
    }

    @Override
    protected void onPostExecute(String params){
        progress.setMessage("Carregado com sucesso");
        ti.retornoWB(params);
        progress.dismiss();
    }
}
