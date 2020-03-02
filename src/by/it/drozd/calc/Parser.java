package by.it.drozd.calc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    Var calc(String expression){
        String[] operands=expression.split(Patterns.OPERATION);

        Var two=Var.createVar(operands[1]);
        if(expression.contains("=")){
            return Var.saveVar(operands[0],two);
        }
        Var one=Var.createVar(operands[0]);
            if(one==null||two==null){
                return null;//TODO create error
            }
            Pattern p= Pattern.compile(Patterns.OPERATION);
            Matcher m=p.matcher(expression);
            if(m.find()){
                String operation=m.group();
                switch (operation){
                    case "+":return one.add(two);
                    case "-":return one.sub(two);
                    case "*":return one.mul(two);
                    case "/":return one.div(two);
                }
            }
            return null;
    }
}
