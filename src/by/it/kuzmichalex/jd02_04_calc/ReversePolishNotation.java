package by.it.kuzmichalex.jd02_04_calc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversePolishNotation {
    private static final Map<String, Integer> priority = new HashMap();

    static {
        priority.put("*", 3);
        priority.put("/", 3);
        priority.put("+", 2);
        priority.put("-", 2);
        priority.put("(", 1);
        priority.put(")", 1);
    }

    private Deque<String> stack = new ArrayDeque<>();
    private Deque<String> outString = new ArrayDeque<>();
    private Deque<Var> varStack = new ArrayDeque<>();

    /**
     * converts expression 1+2/3 into 1 2 3 / -
     */
    void parseExpression(String expression) throws CalcException {
        stack.clear();
        outString.clear();

        int prevPosition = 0;
        String variable;
        String operation;
        Matcher matcher = Pattern.compile(Patterns.OPERATION_PATTERN).matcher(expression);
        do {
            if (matcher.find()) {
                int currPosition = matcher.start();   //Какая-то операция
                if ((currPosition - prevPosition) > 0) {                 //И какое-то выражение
                    variable = expression.substring(prevPosition, currPosition);
                    outString.addLast(variable); //Числа или переменные помещаются в выходную строку
                }
                operation = expression.substring(currPosition, currPosition + 1);
                prevPosition = currPosition + 1;
                processOperator(operation);
            } else {
                variable = expression.substring(prevPosition);
                if (variable.length() > 0) outString.add(variable); //Числа или переменные помещаются в выходную строку
                break;
            }
        } while (true);

        while (stack.size() > 0) {
            String tmp = stack.poll();
            if (tmp.equals("(")) throw new CalcException("Brackets are incorrectly placed");
            outString.add(tmp);
        }
        if (outString.size() == 0) throw new CalcException("Empty expression. Nothing to calculate!");
    }

    private void processOperator(String operation) throws CalcException {
        //Открывающие - в стек
        if (operation.equals("(")) {
            stack.addFirst(operation);
        }
        //закрывающие из стеа
        else if (operation.equals(")")) {
            boolean bracketOk = false;
            while (stack.size() > 0) {
                String tmp = stack.poll();
                if (tmp.equals("(")) {
                    bracketOk = true;
                    break;
                } else outString.add(tmp);
            }
            if (!bracketOk) {
                throw new CalcException("Brackets is little wrong");
            }
        } else {    //какая-то другая операция кроме скобок
            if ((stack.size() > 0) && priority.get(operation) <= priority.get(stack.peek())) {
                while (stack.size() > 0) {
                    if (priority.get(operation) <= priority.get(stack.peek())) {
                        outString.add(stack.poll());
                    } else break;
                }
            }
            stack.addFirst(operation);
        }
    }

    /**
     *
     */
    public Var calc() throws CalcException {
        Var left;
        Var right;
        varStack.clear();
        while (outString.size() > 0) {
            String expression = outString.poll();

            if (expression.matches(Patterns.SCALAR))
                varStack.addFirst(new Scalar(expression));
            else if (expression.matches(Patterns.VECTOR))
                varStack.addFirst(new Vector(expression));
            else if (expression.matches(Patterns.MATRIX))
                varStack.addFirst(new Matrix(expression));
            else if (expression.matches(Patterns.VARNAME))
                varStack.addFirst(Var.getCreatedVar(expression));
            else if (expression.matches(Patterns.OPERATION)) {
                if (varStack.size() < 2) throw new CalcException("Operation needs 2 operands");
                if (expression.equals("+")) {
                    right = varStack.poll();
                    left = varStack.poll();
                    varStack.addFirst(left.add(right));
                } else if (expression.equals("-")) {
                    right = varStack.poll();
                    left = varStack.poll();
                    varStack.addFirst(left.sub(right));
                } else if (expression.equals("*")) {
                    right = varStack.poll();
                    left = varStack.poll();
                    varStack.addFirst(left.mul(right));
                } else if (expression.equals("/")) {
                    right = varStack.poll();
                    left = varStack.poll();
                    varStack.addFirst(left.div(right));
                }
            } else throw new CalcException("Uhhh... What is it: " + expression + "?");
        }
        return varStack.poll();
    }
}