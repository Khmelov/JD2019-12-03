package by.it.barkovsky.jd01_09;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.it.barkovsky.jd01_09.Patterns.OPERATION;

class Parser {
    Var calc(String expression){
        Pattern cal = Pattern.compile(OPERATION);
        String[] split = expression.trim().split(OPERATION);
        Var one = Var.createVar(split[0]);
        Var two = Var.createVar(split[1]);
        if (one == null || two == null){
            System.out.println("ОПЕРАЦИЯ " + expression + " НЕВОЗМОЖНА");
            return null;//TODO CREATE ERROR
        }
        Matcher m = cal.matcher(expression);
        if (m.find()){
            String operation = m.group();
            switch (operation){
                case "+": return one.add(two);
                case "-": return one.sub(two);
                case "*": return one.mul(two);
                case "/": return one.div(two);
            }
        }
        return null;
    }
}
