package by.it.shulga.jd01.jd01_05;

import java.util.Arrays;

import static java.lang.Math.*;

public class TaskC {
    public static void main(String[] args) {
        stepC1();
        System.out.println();
        stepC2();
    }

    private static void stepC1() {
        int sizeA = (int) ((9 - 5.33) / 0.131071);
        double[] arrayA = new double[sizeA];
        for (int i = 0; i < arrayA.length; i++) {
            for (double x = 5.33; x <= 9; x += 0.131071) {
                double z;
                z = cbrt(pow(x, 2) + 4.5);
                if (i >= arrayA.length) break;
                arrayA[i] = z;
                i++;
            }
        }
        int sizeB = 0;
        for (double index : arrayA) {
            if (index > 3.5) sizeB++;
        }
        double[] arrayB = new double[sizeB];
        int x = 0;
        int y = 0;
        while (x < arrayA.length) {
            if (arrayA[x] > 3.5) {
                arrayB[y] = arrayA[x];
                x++;
                y++;
            } else x++;
        }

        System.out.println("Массив А[]");
        printArray(arrayA, "A");
        System.out.println("Массив B[] из элементов массива A > 3.5");
        printArray(arrayB, "B");
        System.out.printf("Среднее геометрическое элементов массива В =% 7.5f", srGeom(arrayB));
    }

    private static void printArray(double[] arr, String name) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%s[% -3d] =% 7.5f ", name, i, arr[i]);
            if ((i + 1) % 5 == 0 || i + 1 == arr.length) System.out.println();
        }
    }

    private static double srGeom(double[] arrayB) {
        double factB = 1;
        int mul = 0;
        do {
            factB *= arrayB[mul];
            mul++;
        } while (mul < arrayB.length - 1);
        return pow(factB, 1.0 / arrayB.length);
    }

    private static void stepC2() {
        int[] arrayA = new int[31];
        for (int i = 0; i < arrayA.length; i++) {
            arrayA[i] = (int) round(random() * 347 + 103);
        }
        System.out.println(Arrays.toString(arrayA));
        int sizeB = 0;
        for (int i = 0; i < arrayA.length; i++) {
            if (arrayA[i] / 10 > i) sizeB++;
        }
        int[] arrayB = new int[sizeB];
        int x = 0;
        int y = 0;
        while (x < arrayA.length) {
            if (arrayA[x] / 10 > x) {
                arrayB[y] = arrayA[x];
                x++;
                y++;
            } else x++;
        }
        Arrays.sort(arrayB);
        System.out.println(Arrays.toString(arrayB));
        System.out.println("Massiv A <index to rows>");
        tablePrintArrayA(arrayA, "A");
        System.out.println("Massiv B <index to cols>");
        tablePrintArrayB(arrayB, "B");

    }

    private static void tablePrintArrayA(int[] arr, String name) {
        System.out.print("╔═══════════╦═══════════╦═══════════╦═══════════╦═══════════╗\n");
        for (int i = 1; i < arr.length - 1; i++) {

            System.out.printf("║ %s[%2d]=%d ", name, i, arr[i]);
            if (i % 5 == 0) {
                System.out.printf("║%n");
                System.out.printf("╠═══════════╬═══════════╬═══════════╬═══════════╬═══════════╣%n");
            }
        }
        System.out.print("║           ║");
        System.out.print("\n╚═══════════╩═══════════╩═══════════╩═══════════╩═══════════╝\n");

    }

    private static void tablePrintArrayB(int[] arr, String name) {
        System.out.print("╔═══════════╦═══════════╗\n");
        for (int i = 0; i <=arr.length/2; i++) {
                System.out.printf("║ %s[%2d]=%d ", name, i, arr[i]);
            if (!((i+1)+arr.length/2==arr.length)) {
                System.out.printf("║ %s[%2d]=%d ║%n", name, (i +1) + arr.length / 2, arr[(i + 1) + arr.length / 2]);
            }else System.out.printf("║           ║%n");
                if (i < arr.length / 2) System.out.printf("╠═══════════╬═══════════╣%n");

        }
        System.out.print("╚═══════════╩═══════════╝");
    }
}
