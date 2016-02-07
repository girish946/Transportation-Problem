package com.example.girish.tproblem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    TextView Solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TableLayout tl = (TableLayout)findViewById(R.id.table);
        tl.addView(getTanbleRowHeading());
        tl.addView(getFirstRow());


        tl.setVerticalScrollBarEnabled(true);

        for(int i =0;i<Problem.rows;i++)
        {
            tl.addView(getRow(i,false));
        }

        tl.addView(getRow(0,true));

        Button calculate = new Button(this);
        TableRow tr = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.span = 10;

        calculate.setText("Calculate");
        calculate.setTextSize(12);
        calculate.setOnClickListener(new View.OnClickListener()
        {    public void onClick(View v)
            {
                buttonClicked();
            }
        });
        tr.addView(calculate);
        tl.addView(tr);

        Solution = new TextView(this);
        Solution.setText("Solution :\n");
        Solution.setTextSize(20);
        Solution.setScroller(new Scroller(this));
        Solution.setMaxLines(20);
        Solution.setMovementMethod(new ScrollingMovementMethod());
        TableRow tr2 = new TableRow(this);
        TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.span = 10;
        tr2.addView(Solution,layoutParams2);
        tl.addView(tr2);

    }


    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
        startActivity(myIntent);
        finish();
        return;
    }

    public void buttonClicked()
    {
        Toast.makeText(this.getApplication() ,
                Problem.printValues()+Problem.printDemand()+Problem.printSupply() , Toast.LENGTH_LONG).show();
        TransportationProblem test = new TransportationProblem(Problem.rows ,Problem.columns);
        for(int i=0;i<Problem.rows;i++)
        {
            test.setStock(Double.parseDouble(Problem.supply[i]),i);
        }

        for(int i=0;i<Problem.columns;i++)
        {
            test.setRequired(Double.parseDouble(Problem.demand[i]),i);
        }

        for(int i=0;i<Problem.rows;i++)
        {
            for (int j=0;j<Problem.columns;j++)
            {
                test.setCost(Double.parseDouble(Problem.values[i][j]),i,j);
            }
        }

        if(Problem.getMethod() == Problem.NORTH_WEST_METHOD) {
            test.northWestCorner();
        }
        else {
            test.leastCostRule();
        }
        String arr[];
        for(Variable t: test.feasible){
            arr = t.toString().split(" ");
            System.out.println(arr[0]);
            System.out.println(arr[1]);
            System.out.println(arr[2]);
            int k = Integer.parseInt(arr[0]);
            int l = Integer.parseInt(arr[1]);
            Problem.Solution[k][l] = arr[2];
        }
        String finalSolution ="Solution :\n------------\n";
        finalSolution = finalSolution+Problem.printSolution();
        finalSolution = finalSolution+"\n------------\n";
        test.getSolution();
        finalSolution = finalSolution+test.getFeasibleSolutionString();
        finalSolution= finalSolution+"Feasible "+test.getSolution()+"\n";

        Solution.setText(finalSolution);
    }

    public TableRow getTanbleRowHeading()
    {
        TableRow tr = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.span=10;
        TextView tabHead = new TextView(this);
        tabHead.setText("Transportation problem");
        tabHead.setTextSize(10);
        tabHead.setGravity(Gravity.CENTER);
        tr.addView(tabHead,layoutParams);
        return  tr;
    }

    public TableRow getFirstRow()
    {
        TableRow tr = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.span=10;

        TextView oo = new TextView(this);
        oo.setText("   / ");
        oo.setTextSize(10);
        tr.addView(oo);


        for(int i =0;i<Problem.columns;i++)
        {
            TextView d = new TextView(this);
            d.setText("   D"+i+"   ");
            d.setTextSize(18);
            tr.addView(d);
        }

        TextView supply = new TextView(this);
        supply.setText(" supply ");
        supply.setTextSize(18);
        tr.addView(supply);

        return  tr;
    }

    public TableRow getRow(int i, Boolean last) {
        final int row_no = i;
        final boolean last_row =last;
        TableRow tr = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.span = 10;


        TextView s = new TextView(this);
        if(!last)
        {
            s.setText("   s" + row_no + "   ");
        }
        else
        {
            s.setText("        ");
        }
        s.setTextSize(18);
        tr.addView(s);

        for (int j = 0; j < Problem.columns; j++) {
            final int col_no = j;
            EditText m = new EditText(this);
            m.setText("");
            m.setInputType(InputType.TYPE_CLASS_NUMBER);
            m.setTextSize(18);
            m.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (!last_row) {

                        Problem.setVal(row_no, col_no, s.toString());
                    } else{
                        Problem.setDemand(col_no,s.toString());
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {

                }
                });

            tr.addView(m);
        }



        if(!last)
        {   EditText d = new EditText((this));
            d.setText("");
            d.setInputType(InputType.TYPE_CLASS_NUMBER);
            d.setTextSize(18);
            d.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Problem.setSupply(row_no,s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });
            tr.addView(d);
        }
        else
        {

            TextView d = new TextView(this);
            d.setText("        ");
            d.setTextSize(18);
            tr.addView(d);
        }


        return tr;
    }
}
