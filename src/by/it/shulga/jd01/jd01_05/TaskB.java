package by.it.shulga.jd01.jd01_05;

import static java.lang.Math.*;

public class TaskB {
    public static void main(String[] args) {
        step1();
        System.out.println();
        step2();
    }

    private static void step1() {
        for (double a = 0; a <= 2; a = a + 0.2) {
            double y = 0;
            for (int x = 1; x <= 6; x++) {
                y += pow(7, a) - cos(x);
            }
            System.out.printf("При a=%3.1f Сумма y =%8.6e%n", a, y);
        }
    }

    private static void step2() {
        for (double x = -5.5; x < 2; x += 0.5) {
            if (x / 2 <= -2 || x / 2 > 0.2) {
                System.out.printf("При x/2=%4.2f вычисления не определены\n", x / 2);
            } else if (x / 2 > -2 || x / 2 <= -1) {
                double b = sin(pow(x, 2));
                double a = log10(abs(b + 2.74));
                System.out.printf("При х/2=%4.2f a =% -8.6e%n", x / 2, a);
            } else if (x / 2 > -1 || x / 2 < 0.2) {
                double b = cos(pow(x, 2));
                double a = log10(abs(b + 2.74));
                System.out.printf("При х/2=%4.2f a =% -8.6e%n", x / 2, a);
            } else if (x / 2 == 0.2) {
                double b = 1 / tan(pow(x, 2));
                double a = log10(abs(b + 2.74));
                System.out.printf("При х/2=%4.2f a =% -8.6e%n", x / 2, a);
            }
        }
    }
}
