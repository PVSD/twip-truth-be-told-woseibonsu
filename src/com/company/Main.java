package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("TruthTables.txt");
        FileWriter fw = new FileWriter(file);

        String input = "(A&&B)";
        TrueFalseVariable[] key =  Operators.GetTruthKey(input, 0);
        String[] TruthTable = getTruthHeader(input, key);

        for (String temp : TruthTable)
            fw.write(temp + "\t\t");
        fw.write("\n");

        for (int i = 0; i < (int)Math.pow(2, Operators.GetVariables(input).size()); i++)
        {
            key =  Operators.GetTruthKey(input, i);
            TruthTable = getTruthTable(input, key);
            for (String temp : TruthTable)
                fw.write(temp + "\t");
            fw.write("\n");
        }

        fw.close();

    }

    public static String[] getTruthHeader(String input, TrueFalseVariable[] truthKey)
    {
        String[] TruthTable = new String[truthKey.length+1];
        TrueFalseVariable[] realTruthKey;
        ArrayList<Object> expression = Operators.GetExpressionArray(input, truthKey);
        boolean answer = true;

        //SETS UP FIRST ROW
        for (int i = 0; i < truthKey.length; i++)
        {
            TruthTable[i] = truthKey[i].variable;
        }
        TruthTable[truthKey.length] = input;



        return TruthTable;
    }

    public static String[] getTruthTable(String input, TrueFalseVariable[] truthKey)
    {
        String[] TruthTable = new String[truthKey.length+1];
        ArrayList<Object> expression = Operators.GetExpressionArray(input, truthKey);
        boolean answer = true;

        for (int i = 0; i < Operators.NumberOfQuantities(input); i++)
        {
            expression = Operators.SolveSimplestQuality(expression);
        }
        TruthTable[truthKey.length] = Boolean.toString((boolean)expression.get(0));

        for(int i = 0; i < truthKey.length; i++)
        {
            TruthTable[i] = Boolean.toString(truthKey[i].value);
        }

        return TruthTable;
    }

}


