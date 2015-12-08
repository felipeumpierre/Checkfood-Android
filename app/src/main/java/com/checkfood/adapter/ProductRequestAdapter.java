package com.checkfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.checkfood.bean.Product;
import com.checkfood.bean.RequestProduct;

import java.text.DecimalFormat;
import java.util.ArrayList;

import senac.checkfood.R;

public class ProductRequestAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<RequestProduct> lista;

    public ProductRequestAdapter(Context context, ArrayList<RequestProduct> lista){
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

<<<<<<< HEAD
    public void removeItem(int position){
        lista.remove(position);
        //notifyDataSetChanged();
    }

=======
>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RequestProduct requestProduct = lista.get(position);

        DecimalFormat df = new DecimalFormat("##0.00");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.products_pedido, null);

        TextView nome = (TextView) layout.findViewById(R.id.txtNome);
        nome.setText(requestProduct.getProduct().getName());

        TextView preco = (TextView) layout.findViewById(R.id.txtPrice);
        preco.setText("R$ "+df.format(requestProduct.getProduct().getPrice()));

        TextView quantity = (TextView) layout.findViewById(R.id.txtQuantity);
        quantity.setText("x"+requestProduct.getQuantity());

        TextView totalPrice = (TextView) layout.findViewById(R.id.txtTotalPrice);
        totalPrice.setText("R$ " + df.format(requestProduct.getProduct().getPrice() * requestProduct.getQuantity()));

        return layout;
    }
}
