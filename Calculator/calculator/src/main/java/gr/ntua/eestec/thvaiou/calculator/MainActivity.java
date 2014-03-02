package gr.ntua.eestec.thvaiou.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText et;

    String numberOne = "";
    String numberTwo = "";
    boolean flag = false;
    String operator;
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.main_editText);


        et.requestFocus();
        et.setCursorVisible(true);
    }

    public void operation(String op) {
        flag = true;
        operator = op;
        et.setText("");
        Log.d(TAG, "operation call");
    }

    public void insert(String i) {
        if (flag==false) {
            numberOne = numberOne + i;
            et.setText(numberOne);
            et.requestFocus();
            Log.d(TAG, "insert number one");
        } else {
            numberTwo = numberTwo + i;
            et.setText(numberTwo);
            et.requestFocus();
            Log.d(TAG, "insert number two");
        }
        return;
    }

    public void equal() {
        if (flag==true) {
            String result;
            float nOne = Float.parseFloat(numberOne);
            float nTwo = Float.parseFloat(numberTwo);
            switch (operator) {
                case "+":
                    result = String.valueOf(nOne+nTwo);
                    et.setText(result);
                    break;
                case "-":
                    result = String.valueOf(nOne-nTwo);
                    et.setText(result);
                    break;
                case "*":
                    result = String.valueOf(nOne*nTwo);
                    et.setText(result);
                    break;
                case "/":
                    if (nTwo == 0) {
                        et.setTextSize(30);
                        et.setText("Operation not valid");
                    } else {
                        result = String.valueOf(nOne/nTwo);
                        et.setText(result);
                    }

            }
        } else {
            numberOne = "";
            numberTwo = "";
            flag = false;
            et.setText("");
            et.requestFocus();
        }
        return;

    }

    public void btn1Clicked(View v) {
        String number = String.valueOf(1);
        insert(number);
        return;
    }

    public void btn2Clicked(View v) {
        String number = String.valueOf(2);
        insert(number);
        return;
    }

    public void btn3Clicked(View v) {
        String number = String.valueOf(3);
        insert(number);
        return;
    }

    public void btn4Clicked(View v) {
        String number = String.valueOf(4);
        insert(number);
        return;
    }

    public void btn5Clicked(View v) {
        String number = String.valueOf(5);
        insert(number);
        return;
    }

    public void btn6Clicked(View v) {
        String number = String.valueOf(6);
        insert(number);
        return;
    }

    public void btn7Clicked(View v) {
        String number = String.valueOf(7);
        insert(number);
        return;
    }

    public void btn8Clicked(View v) {
        String number = String.valueOf(8);
        insert(number);
        return;
    }

    public void btn9Clicked(View v) {
        String number = String.valueOf(9);
        insert(number);
        return;
    }

    public void btn0Clicked(View v) {
        String number = String.valueOf(0);
        insert(number);
        return;
    }

    public void btnCommaClicked(View v) {
        insert(".");
        return;
    }

    public void btnEqClicked(View v) {
        equal();
        return;
    }

    public void btnAddClicked(View v) {
        Log.d(TAG, "Button add clicked");
        operation("+");
        return;
    }

    public void btnSubClicked(View v) {
        operation("-");
        return;
    }

    public void btnMultClicked(View v) {
        operation("*");
        return;
    }

    public void btnDivClicked(View v) {
        operation("/");
        return;
    }

    public void btnDelClicked(View v) {
        numberOne = "";
        numberTwo = "";
        flag = false;
        et.setText("");
        et.requestFocus();
        return;
    }
}

