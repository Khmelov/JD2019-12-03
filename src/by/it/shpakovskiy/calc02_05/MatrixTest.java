package by.it.shpakovskiy.calc02_05;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MatrixTest {
    private String matrix1 = "{{1, 2, -3}, {1, -1, 8.5}, {-4, 5, 6}, {2.5, 3.5, 0}}";
    private String scalar = "2.5";
    private String vector = "{2, -1, 3}";
    private String matrix2 = "{{2, 4, 3}, {0, 1, 0}, {7, 2, 2}, {6, 3, 4}}";

    @Test
    public void testCreateMatrix() throws CalcException {
        Parser parser = new Parser();
        parser.calc("M={{-1.2,23.58, 16}, {0,0, 0.012},{34,-16,-8.55}}");
        String result = parser.calc("M").toString();
        assertEquals("{{-1.2, 23.58, 16.0}, {0.0, 0.0, 0.012}, {34.0, -16.0, -8.55}}", result);
    }

    @Test
    public void testMatrixAddScalar() throws CalcException {
        Parser parser = new Parser();
        String result = parser.calc(matrix1 + "+" + scalar).toString();
        String expected = "{{3.5, 4.5, -0.5}, {3.5, 1.5, 11.0}, {-1.5, 7.5, 8.5}, {5.0, 6.0, 2.5}}";
        assertEquals(expected, result);
    }

    @Test
    public void testMatrixSubScalar() throws CalcException {
        Parser parser = new Parser();
        String result = parser.calc(matrix1 + "-" + scalar).toString();
        String expected = "{{-1.5, -0.5, -5.5}, {-1.5, -3.5, 6.0}, {-6.5, 2.5, 3.5}, {0.0, 1.0, -2.5}}";
        assertEquals(expected, result);
    }

    @Test
    public void testMatrixMulScalar() throws CalcException {
        Parser parser = new Parser();
        String result = parser.calc(matrix1 + "*" + scalar).toString();
        String expected = "{{2.5, 5.0, -7.5}, {2.5, -2.5, 21.25}, {-10.0, 12.5, 15.0}, {6.25, 8.75, 0.0}}";
        assertEquals(expected, result);
    }

    @Test
    public void testMatrixDivScalar() throws CalcException {
        Parser parser = new Parser();
        String result = parser.calc(matrix1 + "/" + scalar).toString();
        String expected = "{{0.4, 0.8, -1.2}, {0.4, -0.4, 3.4}, {-1.6, 2.0, 2.4}, {1.0, 1.4, 0.0}}";
        assertEquals(expected, result);
    }

    @Test(expected = CalcException.class)
    public void testMatrixAddVector() throws CalcException {
        Parser parser = new Parser();
        parser.calc(matrix1 + "+" + vector);
        fail();
    }

    @Test(expected = CalcException.class)
    public void testMatrixSubVector() throws CalcException {
        Parser parser = new Parser();
        parser.calc(matrix1 + "-" + vector);
        fail();
    }

    @Test(expected = CalcException.class)
    public void testMatrixMulVectorWithWrongSize() throws CalcException {
        Parser parser = new Parser();
        parser.calc(matrix1 + "*{2, -1, 3, 4}");
        fail();
    }

    @Test
    public void testMatrixMulVector() throws CalcException {
        Parser parser = new Parser();
        String result = parser.calc(matrix1 + "*" + vector).toString();
        assertEquals("{-9.0, 28.5, 5.0, 1.5}", result);
    }

    @Test(expected = CalcException.class)
    public void testMatrixDivVector() throws CalcException {
        Parser parser = new Parser();
        parser.calc(matrix1 + "/" + vector);
        fail();
    }

    @Test
    public void testMatrixAddMatrix() throws CalcException {
        Parser parser = new Parser();
        String result = parser.calc(matrix1 + "+" + matrix2).toString();
        String expected = "{{3.0, 6.0, 0.0}, {1.0, 0.0, 8.5}, {3.0, 7.0, 8.0}, {8.5, 6.5, 4.0}}";
        assertEquals(expected, result);
    }

    @Test(expected = CalcException.class)
    public void testMatrixAddMatrixWithWrongSize() throws CalcException {
        Parser parser = new Parser();
        parser.calc(matrix1 + "+" + "{{1,1,1},{1,1,1},{1,1,1}}");
        fail();
    }

    @Test
    public void testMatrixSubMatrix() throws CalcException {
        Parser parser = new Parser();
        String result = parser.calc(matrix1 + "-" + matrix2).toString();
        String expected = "{{-1.0, -2.0, -6.0}, {1.0, -2.0, 8.5}, {-11.0, 3.0, 4.0}, {-3.5, 0.5, -4.0}}";
        assertEquals(expected, result);
    }

    @Test(expected = CalcException.class)
    public void testMatrixSubMatrixWithWrongSize() throws CalcException {
        Parser parser = new Parser();
        parser.calc(matrix1 + "-" + "{{1,1,1},{1,1,1},{1,1,1}}");
        fail();
    }

    @Test
    public void testMatrixMulMatrix() throws CalcException {
        Parser parser = new Parser();
        String result = parser.calc("{{2, -1, 8}, {-6, 2, 1}}*{{1, -2}, {3, 4}, {0, 5}}").toString();
        String expected = "{{-1.0, 32.0}, {0.0, 25.0}}";
        assertEquals(expected, result);
    }

    @Test(expected = CalcException.class)
    public void testMatrixMulMatrixWithWrongSize() throws CalcException {
        Parser parser = new Parser();
        parser.calc(matrix1 + "*" + "{{1,1,1},{1,1,1}}");
        fail();
    }
}