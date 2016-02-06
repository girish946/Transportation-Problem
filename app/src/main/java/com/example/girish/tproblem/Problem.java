package com.example.girish.tproblem;

/**
 * Created by girish on 4/2/16.
 */

public class Problem {

    public static int rows = 0;
    public static int columns = 0;
    public static String values[][];

    public static String[] demand;
    public static String[] supply;

    public static String Solution[][];

    public static int NORTH_WEST_METHOD=0;
    public static int LEAST_COST_METHOD=1;
    public static int Method = 0;

    public Problem() {
        super();
    }

    public static void setColumns(int cols) {
        Problem.columns = cols;
    }

    public static void setRows(int rows) {
        Problem.rows = rows;
    }

    public static int getColumns() {
        return Problem.columns;
    }

    public static int getRows() {
        return Problem.rows;
    }

    public static void setMethod(int method)
    {
        Method = method;
    }

    public static int getMethod()
    {
        return Method;
    }

    public static void initValues() {
        if (rows != 0 && columns != 0) {
            values = new String[rows][columns];
            Solution = new String[rows][columns];
            demand = new String[columns];
            supply = new String[rows];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    values[i][j] = "0";
                    Solution[i][j] = "0";
                }
            }
            for (int i = 0; i < columns; i++) {
                demand[i] = "0";
            }

            for (int i = 0; i < rows; i++) {
                supply[i] = "0";
            }


        }
    }

    public static void setVal(int row, int column, String val) {
        values[row][column] = val;
        //printValues();
    }

    public static void setDemand(int i, String val) {
        demand[i] = val;
    }

    public static void setSupply(int i, String val) {
        supply[i] = val;
    }


    public static String printValues() {
        String s = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                s = s + values[i][j] + "  ";
            }
            s = s + "\n";
        }
        return s;
    }

    public static String printSolution()
    {
        String s = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                s = s +" " +Solution[i][j] + "  ";
            }
            s = s + "\n";
        }
        return s;
    }

    public static String printDemand() {
        String s = "Demand :\n";
        for (int i = 0; i < columns; i++) {
            s = s + demand[i] + "  ";
        }
        return s+"\n";
    }

    public static String printSupply()
    {
        String s = "Supply :\n";
        for(int i=0;i<rows;i++)
        {
            s =s+supply[i]+"  ";
        }
        return s+"\n";
    }
}
