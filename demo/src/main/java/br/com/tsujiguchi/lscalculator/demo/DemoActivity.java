package br.com.tsujiguchi.lscalculator.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.tsujiguchi.lscalculator.view.LSDisplay;
import br.com.tsujiguchi.lscalculator.view.LSFunctionPadLayout;
import br.com.tsujiguchi.lscalculator.view.LSNumberPadLayout;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        LSDisplay display = (LSDisplay) findViewById(R.id.lsCalculatorDisplay);
        LSNumberPadLayout numberPad = (LSNumberPadLayout) findViewById(R.id.lsCalculatorNumberPad);
        LSFunctionPadLayout functionPad = (LSFunctionPadLayout) findViewById(R.id.lsCalculatorFunctionPad);

        numberPad.setOnSendCharacterListener(display);
        functionPad.setOnSendCharacterListener(display);
    }

}
