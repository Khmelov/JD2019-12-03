package by.it.plugatar.calc;
/*          Sacalar       Vector            Matrix
* Scalar   +-* делить     +-* делить нет    +-* делить нет
*
* Vector   +-*делить мож  +-*делить нет     нет нет нет нет
*
* Matrix   +-*делить с      умножение с огран + - * с ограничени
*           ограничен
* */
/*
abstract class Var {
    @Override
    public String toString(){
        return "Это класс Var";
    }
}
*/



import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Var implements Operation {

   private static Map<String, Var> vars = new HashMap<>();

   static Var saveVar (String name, Var var){
        vars.put(name, var);
        return var;
    }


    static Var createVar(String s) throws CalcException{
        //s = s.trim();
        s = s.trim().replace("\\s+","");
        if (s.matches(Patterns.SCALAR))
            return new Scalar(s);
        else if (s.matches(Patterns.VECTOR))
            return new Vector(s);
        else if (s.matches(Patterns.MATRIX))
            return new Matrix(s);
        else if (vars.containsKey(s)) {
            Var var = vars.get(s);
            if (var != null) {
                return var;
            }
        }
        else {
            System.out.println("Переменная " + s + " не определена");
            }
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.INIT)+" "+s);//return null
       // (vars.containsKey(s))
       // return vars.get(s);//Если содержит, то вернем соответствующий объект
    }

    static void save(String key, Var value) throws CalcException {
        vars.put(key,value);
        VarFile.save(vars);
    }

    static void printvar() {
        for (Map.Entry<String, Var> stringVarEntry : vars.entrySet()) {
            System.out.println(stringVarEntry.getKey()+"="+stringVarEntry.getValue());
        }
    }
    static void sortvar() {
        TreeMap<String, Var> varTreeMap = new TreeMap<>(vars);
        for (Map.Entry<String, Var> entry : varTreeMap.entrySet()) {
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }
    @Override
    public String toString() {
        return "Var{}";
    }

    @Override
    public Var add(Var other) throws CalcException{
        //System.out.println("Операция сложения" + this + "+" + other + " невозможна");
        //throw new CalcException("Операция сложения" + this + "+" + other + " невозможна");
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.ADDITION)+ this + "+" + other);
        //return null;
    }

    @Override
    public Var sub(Var other) throws CalcException {
        //System.out.println("Операция вычитания" + this + "+" + other + " невозможна");
        //return null;
        //throw new CalcException("Операция вычитания" + this + "+" + other + " невозможна");
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.SUBTRACTION) + this + "+" + other);

    }

    @Override
    public Var mul(Var other) throws CalcException{
        //System.out.println("Операция умножения" + this + "+" + other + " невозможна");
        //return null;
        //throw new CalcException("Операция умножения" + this + "+" + other + " невозможна");
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.MULTIPLICATION) + this + "+" + other);
    }

    @Override
    public Var div(Var other) throws CalcException{
        //System.out.println("Операция деления" + this + "+" + other + " невозможна");
        //return null;
       // throw new CalcException("Операция деления" + this + "+" + other + " невозможна");
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.DIVISION)+ this + "+" + other);
    }
}



