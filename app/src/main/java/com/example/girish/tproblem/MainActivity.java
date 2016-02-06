package com.example.girish.tproblem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Button start = (Button)findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener()
        {    public void onClick(View v)
        {
            TextView rowsText = (TextView)findViewById(R.id.editText2);
            TextView colsText = (TextView)findViewById(R.id.editText);
            Problem.setColumns(Integer.parseInt(colsText.getText().toString()));
            Problem.setRows(Integer.parseInt(rowsText.getText().toString()));
            Problem.initValues();
            RadioGroup rg = (RadioGroup)findViewById(R.id.rbg);
            int selectedId=rg.getCheckedRadioButtonId();
            RadioButton method =(RadioButton) findViewById(selectedId);
            if(method.getText().equals("NorthWest")){
                Problem.setMethod(Problem.NORTH_WEST_METHOD);
            }
            else
            {
                Problem.setMethod(Problem.LEAST_COST_METHOD);
            }
            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intent);
            finish();
        }
        });
    }
}
