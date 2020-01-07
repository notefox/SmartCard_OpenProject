package com.example.nfcapp;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ButtonControl implements TextWatcher {

    private EditText t1;
    private EditText t2;
    private EditText t3;
    private EditText t4;
    private Button button;

    public ButtonControl(View t1, View t2, View t3, View t4, View b) {
        this.t1 = (EditText) t1;
        this.t2 = (EditText) t2;
        this.t3 = (EditText) t3;
        this.t4 = (EditText) t4;
        this.button = (Button) b;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (t1.length() > 0 && t2.length() > 0 && t3.length() > 0 && t4.length() > 0)
            button.setClickable(true);
        else
            button.setClickable(false);
    }
}
