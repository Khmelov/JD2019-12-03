package by.it.cherkas.jd01_05;


import static java.lang.Math.*;

public class TaskB {
    public static void main(String[] args) {


        for (double a = 0; a <= 2; a += 0.2) {
            int s = 1;
            if (s <= 6) {
                double y = s * (pow(7, a));
                s++;
                for (double x = 1; x <= 6; x++) {
                    y = y - cos(x);
                    System.out.printf("при a=%6.2f f=%g\n", a, y);
                }
            }
        }

        for (double x = -5.5; x < 2; x += 0.5) {
            if (x / 2 > -2 && x / 2 <= -1) {
                double b=0;
                if (x/2<-2 || x/2>0.2) {
                    System.out.println("При х/2=" +x/2 + "вычисления не определены");
                    continue;
                }
                else if (x/2>-2 && x/2<=-1) {
                    b=sin(pow(x,2));
                }
                else if (x/2>-1 && x/2<0.2) {
                    b=cos(pow(x,2));
                }
                else if (x/2==0.2) {
                    b=cos(pow(x,2))/sin(pow(x,2));
                }
                double a = log10(abs(b+2.74));
                System.out.println("При х/2=" + x/2 + " a = " + a);
            }
            }


        }
    }

