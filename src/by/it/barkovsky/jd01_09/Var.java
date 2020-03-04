package by.it.barkovsky.jd01_09;

public class Var implements Operation {
    static Var createVar(String s) {
        //s = s.trim();
        s = s.trim().replace("\\s+","");
        if (s.matches(Patterns.SCALAR)) return new Scalar(s);
        if (s.matches(Patterns.VECTOR)) return new Vector(s);
        if (s.matches(Patterns.MATRIX)) return new Matrix(s);
        System.out.println("Переменная " + s + " не определена");
        return null;
    }
    @Override
    public Var add(Var other) {
        System.out.println("Операция сложения" + this + "+" + other + " невозможна");
        return null;
    }

    @Override
    public Var sub(Var other) {
        System.out.println("Операция вычитания" + this + "+" + other + " невозможна");
        return null;
    }

    @Override
    public Var mul(Var other) {
        System.out.println("Операция умножения" + this + "+" + other + " невозможна");
        return null;
    }

    @Override
    public Var div(Var other) {
        System.out.println("Операция деления" + this + "+" + other + " невозможна");
        return null;
    }
}



