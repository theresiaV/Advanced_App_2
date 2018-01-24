package com.tere_mary.advanced_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Transaction extends Fragment implements View.OnClickListener {

    DatabaseHelper myDB;
    View v;

    EditText descEx, amountEx;
    Button AddEx;

    EditText descIn,amountsIn;
    Button AddIn;

    public Fragment_Transaction() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment__transaction, container, false);

        myDB = new DatabaseHelper(getActivity());

        descEx = (EditText) v.findViewById(R.id.EditText_DeskripsiExpnses);
        amountEx = (EditText) v.findViewById(R.id.EditText_AmountExpenses);

        descIn = (EditText) v.findViewById(R.id.EditText_DeskripsiIncome);
        amountsIn = (EditText) v.findViewById(R.id.EditText_AmountIncome);

        AddEx = (Button) v.findViewById(R.id.Button_AddExpenses);
        AddIn = (Button) v.findViewById(R.id.Button_AddIncome);

        AddEx.setOnClickListener(this);
        AddIn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Button_AddExpenses:

                //kalau input description/amount dari expenses kosong
                if (descEx.getText().toString().length() == 0) {
                    descEx.setError("Description Expenses not found");
                    break;
                } else if (amountEx.getText().toString().length() == 0){
                    amountEx.setError("Amount Expenese not found");
                    break;
                }

                //kalau input description/amount dari expenses ada inputnya
                if (descEx.getText().toString().length() != 0 || amountEx.getText().toString().length() != 0){
                        boolean resultEx = myDB.save_expenses(descEx.getText().toString(),
                                amountEx.getText().toString());

                        if (resultEx) {
                            Toast.makeText(getActivity(), "New expenses add succes", Toast.LENGTH_LONG).show();
                            descEx.setText("");
                            amountEx.setText("");
                            descEx.requestFocus();
                            break;
                        } else {
                            Toast.makeText(getActivity(), "Fails Add", Toast.LENGTH_LONG).show();
                        }
                    break;
                }

            case R.id.Button_AddIncome:

                //kalau input description/amount dari income kosong
                if (descIn.getText().toString().length() == 0) {
                    descIn.setError("Description Income not found");
                } else if (amountsIn.getText().toString().length() == 0){
                    amountsIn.setError("Amount Income not found");
                }

                //kalau input description/amount dari income ada inputnya
                if (descIn.getText().toString().length() != 0 || amountsIn.getText().toString().length() != 0){
                    boolean resultIn = myDB.save_income(descIn.getText().toString(), amountsIn.getText().toString());

                    if (resultIn) {
                        Toast.makeText(getActivity(), "New income add succes", Toast.LENGTH_LONG).show();
                        descIn.setText("");
                        amountsIn.setText("");
                        descIn.requestFocus();
                        break;
                    } else {
                        Toast.makeText(getActivity(), "Fails Add", Toast.LENGTH_LONG).show();
                    }
                    break;
                }

            }
        }
}
