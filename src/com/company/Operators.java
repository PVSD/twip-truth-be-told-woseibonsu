package com.company;

import java.util.ArrayList;

public class Operators {

    public static TrueFalseVariable[] GetTruthKey (String input, int iteration)
    {
        TrueFalseVariable[] truthKey = new TrueFalseVariable[GetVariables(input).size()];
        ArrayList<String> variableKey = new ArrayList<>();
        int i2 = 0;
        for (TrueFalseVariable temp: truthKey)
        {
            variableKey.add(String.valueOf((char)GetVariables(input).get(i2)));
            i2++;
        }

        String key = Integer.toBinaryString(iteration); //GENERATES THE KEY OF TRUTH
        while (key.length() < truthKey.length)
        {
            key += "0" + key;
        }

        for (int i = 0; i < truthKey.length; i++)
        {
            truthKey[i] = new TrueFalseVariable(true, "X");
            truthKey[i].variable = variableKey.get(i);
            if(key.substring(i,i+1).equals("0"))
            {
                truthKey[i].value = false;
            }
            else
            {
                truthKey[i].value = true;
            }
        }

        return truthKey;
    }

    public static ArrayList<Object> GetExpressionArray(String input, TrueFalseVariable... variableKey) {


        ArrayList<Object> expression = new ArrayList<>();


        for (int i = 0; i < input.length(); i++)
        {
            for (TrueFalseVariable temp : variableKey){
                if (input.substring(i, i + 1).equals(temp.variable))
                    expression.add(new Boolean(temp.value));
            }
            if (input.substring(i, i + 1).equals("("))
                expression.add("(");
            else if (input.substring(i, i + 1).equals(")"))
                expression.add(")");
            else if (input.substring(i, i + 1).equals("&")) {
                expression.add("&&");
                i++;}
            else if (input.substring(i, i + 1).equals("|")) {
                expression.add("||");
                i++;}
        }


        return expression;
    }
    public static int NumberOfQuantities(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++)
        {
            if (input.charAt(i) == '(')
                count++;
        }
        return count;
    }

    public static ArrayList<Character> GetVariables(String input) {

        ArrayList<Character> variables = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            String s = input.substring(i, i + 1);
            if (s.matches("[a-zA-Z]")) {
                if (!variables.contains(s.toCharArray()[0]))
                    variables.add(s.toCharArray()[0]);
            }
        }
        return variables;
    }

    public static ArrayList<Object> SolveSimplestQuality(ArrayList<Object> input) {

        ArrayList<Object> expression = input;
        Boolean answer = false;

        int parCount = 0;
        ArrayList<Integer> indexOfBegParatheses = new ArrayList<>();
        ArrayList<Integer> indexOfEndParatheses = new ArrayList<>();
        for (int i = 0; i < expression.size(); i++) {
            if (expression.get(i).equals("(")) {
                parCount++;
                indexOfBegParatheses.add(i);
            }
            if (expression.get(i).equals(")")) {
                parCount++;
                indexOfEndParatheses.add(i);
            }
        }
        ArrayList<Object> newExpression = new ArrayList<>(expression.subList(indexOfBegParatheses.get(indexOfBegParatheses.size() - 1) + 1, indexOfEndParatheses.get(0)));
//        System.out.println("CURRENT QUANTITY: " + newExpression);
//        System.out.println("WHAT IS THE COMPARATOR: " + newExpression.get(1));

        if (newExpression.get(1).toString().equals("&&")) {
            if (Boolean.compare((boolean) newExpression.get(0), (boolean) newExpression.get(2))
                    != 0) {
                answer = false;
            } else {

                answer = (boolean) newExpression.get(0);
            }
        }
        if (newExpression.get(1).toString().equals("||")) {
            if ((boolean) newExpression.get(0) || (boolean) newExpression.get(2)) {
                answer = true;
            } else {
                answer = false;
            }
        }

        ArrayList<Object> finalArray = new ArrayList<>();
        for (int i = 0; i < indexOfBegParatheses.get(indexOfBegParatheses.size()-1); i++)
        {
            finalArray.add(expression.get(i));
        }
        finalArray.add(answer);
        for (int i = indexOfEndParatheses.get(0)+1; i < expression.size(); i++)
        {
            finalArray.add(expression.get(i));
        }
        return finalArray;
    }
}
