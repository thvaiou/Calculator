package gr.ntua.eestec.thvaiou.calculator2;

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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    EditText et;

    String numberOne = "";
    String numberTwo = "";
    boolean flag = false;
    String operator = "";
    private static final String TAG = "myLogs";
    int textLength;
    String presentation;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.main_editText);
        et.setCursorVisible(true);
        et.setRawInputType(InputType.TYPE_CLASS_TEXT);
        et.setTextIsSelectable(true);

        delete = (Button) findViewById(R.id.main_Delete);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super .onCreateOptionsMenu(menu);
    }

    public void operation(String op) {
        flag = true;
        operator = op;
        presentation=numberOne + operator;
        et.setText(presentation);
        textLength = et.getText().length();
        et.setSelection(textLength, textLength);
        Log.d(TAG, "operation call");
    }

    public void insert(String i) {
        if (flag==false) {
            numberOne = numberOne + i;
            presentation = numberOne;
            et.setText(presentation);
            textLength = et.getText().length();
            et.setSelection(textLength, textLength);
            Log.d(TAG, "insert number one");
        } else {
            numberTwo = numberTwo + i;
            presentation = numberOne + operator + numberTwo;
            et.setText(presentation);
            textLength = et.getText().length();
            et.setSelection(textLength, textLength);
            Log.d(TAG, "insert number two");
        }
        return;
    }

    public void equal() {
        if (numberTwo.equals("") && operator.equals("")) {
            et.setText(presentation);
            textLength = et.getText().length();
            et.setSelection(textLength, textLength);
        } else if (numberTwo.equals("") && !operator.equals("")) {
            et.setText(numberOne);
            textLength = et.getText().length();
            et.setSelection(textLength, textLength);
        } else {
            
        }


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
        operation("×");
        return;
    }

    public void btnDivClicked(View v) {
        operation("÷");
        return;
    }

    public void btnDelClicked(View v) {
        if (presentation.equals("")) {
            numberOne = "";
            numberTwo = "";
            et.setText("");
        } else {
            presentation = presentation.substring(0, presentation.length()-1);
            et.setText(presentation);
            textLength = et.getText().length();
            et.setSelection(textLength, textLength);
            String[] parts;
            if (presentation.contains("-")) {
                parts = presentation.split("-");
                numberOne = parts[0];
                numberTwo = parts[1];
            } else if (presentation.contains("×")) {
                parts = presentation.split("×");
                numberOne = parts[0];
                numberTwo = parts[1];
            } else if (presentation.contains("÷")) {
                parts = presentation.split("÷");
                numberOne = parts[0];
                numberTwo = parts[1];
            } else if (presentation.contains("+")) {
                parts = presentation.split("\\+");
                numberOne = parts[0];
                numberTwo = parts[1];
            } else {
                numberOne = presentation;
            }
        }
        return;
    }
}