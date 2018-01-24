package com.tere_mary.advanced_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Theresia V A Mary G on 1/23/2018.
 */

public class ListViewEx extends BaseAdapter{

    Context context;
    ArrayList<String> desE;
    ArrayList<Integer> amE;

    public ListViewEx(Context context, ArrayList<String> desE, ArrayList<Integer> amE){
        this.context = context;
        this.desE = desE;
        this.amE = amE;
    }

    @Override
    public int getCount() {
        return desE.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View child, ViewGroup parent) {
        Holder holder;
        LayoutInflater inflater;

        if (child == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = inflater.inflate(R.layout.row_ex, null);

            holder = new Holder();
            holder.txt_des = (TextView) child.findViewById(R.id.TextView_DescREx);
            holder.txt_am = (TextView) child.findViewById(R.id.TextView_AmountREx);

            child.setTag(holder);
        } else {
            holder = (Holder) child.getTag();
        }

        holder.txt_des.setText(desE.get(position));
        holder.txt_am.setText(String.valueOf(amE.get(position)));
        return child;
    }

    private class Holder{
        TextView txt_des;
        TextView txt_am;
    }
}
