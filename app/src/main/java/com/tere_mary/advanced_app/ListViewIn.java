package com.tere_mary.advanced_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Theresia V A Mary G on 1/24/2018.
 */

public class ListViewIn extends BaseAdapter{

    Context context;
    ArrayList<String> desI;
    ArrayList<Integer> amI;

    public ListViewIn(Context context, ArrayList<String> desI, ArrayList<Integer> amI){
        this.context = context;
        this.desI = desI;
        this.amI = amI;
    }

    @Override
    public int getCount() {
        return desI.size();
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
            child = inflater.inflate(R.layout.row_in, null);

            holder = new Holder();
            holder.txt_des = (TextView) child.findViewById(R.id.TextView_DescRIn);
            holder.txt_am = (TextView) child.findViewById(R.id.TextView_AmountRIn);

            child.setTag(holder);
        } else {
            holder = (Holder) child.getTag();
        }

        holder.txt_des.setText(desI.get(position));
        holder.txt_am.setText(String.valueOf(amI.get(position)));
        return child;
    }

    private class Holder{
        TextView txt_des;
        TextView txt_am;
    }
}
