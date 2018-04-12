package io.github.michalsmolarek.tipcalculator;

import java.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // piewrszy wiersz

    private EditText amountEditText;
    private TextView amoutTextView;

    // drugi wiersz

    private TextView percentTextView;
    private SeekBar percentSeekBar;

    // trzeci wiersz

    private TextView tipLabelTextView;
    private TextView tipTextView;

    // czwarty wiersz

    private TextView totalLabelTextView;
    private TextView totalTextView;


    // statyczne obiekty do formatowania wartosci walutowych i procentowych

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    // kwota rachunku podana przez usera

    private double billAmount = 0.0;

    private double tipPercent = 0.15;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicjalizacja widoków

        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amoutTextView = (TextView) findViewById(R.id.amountTextView);

        percentTextView = (TextView) findViewById(R.id.percentTextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);

        tipLabelTextView = (TextView) findViewById(R.id.tipLabelTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);

        totalLabelTextView = (TextView) findViewById(R.id.tipLabelTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        // nasłuchiwanie zdarzen dla pola edittext

        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                try{
                    billAmount = Double.parseDouble(charSequence.toString()) / 100.0;
                    amoutTextView.setText(currencyFormat.format(billAmount));

                }catch (NumberFormatException e){
                    amoutTextView.setText("err");
                    billAmount = 0.0;
                }

                calculateTipAndTotalAmount();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });

        // nasłuhiwanie dla seekbar
        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                tipPercent = i / 100.0;
                calculateTipAndTotalAmount();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void calculateTipAndTotalAmount(){

        double tipAmount = billAmount * tipPercent;
        double totalAMount = billAmount + tipAmount;

        percentTextView.setText(percentFormat.format(tipPercent));
        tipTextView.setText(currencyFormat.format(tipAmount));
        totalTextView.setText(currencyFormat.format(totalAMount));

    }
}
