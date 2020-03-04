package by.it.plugatar.calc;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

//import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
   /*
    @Before

    public static void main(String[] args) {
        System.out.println("Start");
    }
    */
    /*
    @Test
    public void add() throws CalcException {
        Parser parser = new Parser();
        String actualA = parser.calc("3+2");
        assertEquals("5.0",actualA);
    }

    @Test
    public void sub() throws CalcException {
        Parser parser = new Parser();
        //тут ваши выражения
        String actualA = parser.calc("8-6");
        assertEquals("2.0",actualA);
    }

    @Test
    public void mul() throws CalcException {
        Parser parser = new Parser();
        String actualA = parser.calc("11*12");
        assertEquals("132.0",actualA);
    }
*/
    @Test
    public void div() throws Exception {
        Parser parser = new Parser();
        String expression = "18/4";
        double expected = 4.5;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Ошибка " + expression, expected, actual, 1e-5);
        //String actualA = parser.calc("18/4");
        //assertEquals("4.5",actualA);
    }
}