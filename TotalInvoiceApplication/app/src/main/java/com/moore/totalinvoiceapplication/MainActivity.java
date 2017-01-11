package com.moore.totalinvoiceapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
implements TextView.OnEditorActionListener {

    // Define Instance Variables
    private EditText subtotalET;
    private TextView percentTV;
    private TextView discountPercentTV;
    private TextView totalTV;

    private String subtotalString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Reference to the widgets
        subtotalET = (EditText) findViewById(R.id.subtotalET);
        percentTV = (TextView) findViewById(R.id.percentTV);
        discountPercentTV = (TextView) findViewById(R.id.discountPercentTV);
        totalTV = (TextView) findViewById(R.id.totalTV);

        // Set the EditText event handler
        subtotalET.setOnEditorActionListener(this);
    }
    
    @Override
    public boolean onEditorAction (TextView textView, int actionID , KeyEvent keyEvent){
        if (actionID == EditorInfo.IME_ACTION_DONE || actionID == EditorInfo.IME_ACTION_UNSPECIFIED){

            calculateAndDisplay();

        }
        return false;
    }

    private void calculateAndDisplay() {

        subtotalString = subtotalET.getText().toString();
        float subtotal;
        if (subtotalString.equals("")) {
            subtotal = 0;
        } else{
            subtotal = Float.parseFloat(subtotalString);
        }

        //Preform Calculations
        float discount;
        if(subtotal >= 200){
            discount = .2f;
        } else if(subtotal >= 100){
            discount = .1f;
        } else {
            discount = 0;
        }

        float discountAmount = subtotal * discount;
        float total = subtotal - discountAmount;

        //Display data on the Layout

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTV.setText(percent.format(discount));

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        discountPercentTV.setText(currency.format(discountAmount));
        totalTV.setText(currency.format(total));

    }
}
