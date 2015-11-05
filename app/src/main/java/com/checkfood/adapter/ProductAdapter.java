package com.checkfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.checkfood.bean.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import senac.checkfood.R;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> lista;

    public ProductAdapter(Context context, ArrayList<Product> lista){
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

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Product product = lista.get(position);

        DecimalFormat df = new DecimalFormat("##0.00");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.products, null);

        TextView nome = (TextView) layout.findViewById(R.id.txtProduct);
        nome.setText(product.getName());

        TextView category = (TextView) layout.findViewById(R.id.txtCategory);
        category.setText(product.getCategory().getName());

        TextView preco = (TextView) layout.findViewById(R.id.txtPrice);
        preco.setText("R$ "+df.format(product.getPrice()));

        ImageView image = (ImageView) layout.findViewById(R.id.imgProduct);
        image.setImageResource(product.getImage(position));

        return layout;
    }
}
