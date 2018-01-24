package com.tere_mary.advanced_app;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Dashboard extends Fragment{

    DatabaseHelper myDB;
    Cursor cursor;
    View v;

    //expenses
    ListViewEx listViewEx;
    ArrayList<String> descExList = new ArrayList<String>();
    ArrayList<Integer> amountExList = new ArrayList<Integer>();
    ListView expensesList;
    TextView totalEx,totalIn,balance;

    //income
    ListViewIn listViewIn;
    ArrayList<String> descInList = new ArrayList<String>();
    ArrayList<Integer> amountInList = new ArrayList<Integer>();
    ListView incomeList;

    public Fragment_Dashboard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_dashboard, null);

        expensesList = (ListView) v.findViewById(R.id.ListView_Expenses);
        incomeList = (ListView) v.findViewById(R.id.ListView_Income);

        totalEx = (TextView) v.findViewById(R.id.TextView_TotalEx);
        totalIn = (TextView) v.findViewById(R.id.TextView_TotIn);

        balance = (TextView) v.findViewById(R.id.TextView_Balance);

        myDB = new DatabaseHelper(getActivity());

        loadLEx();
        loadLIn();

        //balance atau tidak
        int bal;
        bal = getTotal(amountInList) - getTotal(amountExList);

        balance.setText(String.valueOf(bal));

        return v;
    }

    public void loadLEx(){
        cursor = myDB.list_expenses();

        if (cursor.getCount() == 0) {
            alert_message("Message", "Data expenses not found");
            return;
        }

        descExList.clear();
        amountExList.clear();

        if (cursor.moveToFirst()){
            do {
                descExList.add(cursor.getString(cursor.getColumnIndex(myDB.COLE_2)));
                amountExList.add(Integer.valueOf(cursor.getString(cursor.getColumnIndex(myDB.COLE_3))));

            }while (cursor.moveToNext());
        }

        listViewEx = new ListViewEx(getActivity(), descExList, amountExList);
        expensesList.setAdapter(listViewEx);

        totalEx.setText(String.valueOf(getTotal(amountExList)));
    }

    public void loadLIn(){
        cursor = myDB.list_income();

        if (cursor.getCount() == 0) {
            alert_message("Message", "Data income not found");
            return;
        }

        descInList.clear();
        amountInList.clear();

        if (cursor.moveToFirst()){
            do {
                descInList.add(cursor.getString(cursor.getColumnIndex(myDB.COL_2)));
                amountInList.add(Integer.valueOf(cursor.getString(cursor.getColumnIndex(myDB.COL_3))));
            }while (cursor.moveToNext());
        }

        listViewIn = new ListViewIn(getActivity(), descInList, amountInList);
        incomeList.setAdapter(listViewIn);

        totalIn.setText(String.valueOf(getTotal(amountInList)));
    }

    public int getTotal(ArrayList<Integer> list){
        int total = 0;
        for (int i = 0; i < list.size(); i++){
            total = total + Integer.parseInt(String.valueOf(list.get(i)));
        }
        return total;
    }

    public void alert_message(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
