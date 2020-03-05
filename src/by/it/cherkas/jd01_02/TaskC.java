package by.it.cherkas.jd01_02;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TaskC {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int[][] array = step1(n);
        step2(array);
        step3(array);
    }

    private static int[][] step1(int n) {
        int[][] mas = new int[n][n];
        int counterPlus = 0;
        int counterMinus = 0;
        for (int i = 0; i < mas.length ; i++) {
            for (int j = 0; j < mas.length ; j++) {
                Random random = new Random();
                if (random.nextBoolean()) {
                    mas[i][j] = random.nextInt(n + 1);
                }
                else {
                    mas[i][j] = 0 - random.nextInt(n + 1);
                }
                if (mas[i][j] == n) {
                    counterPlus++;
                }
                else if (mas[i][j] == 0 - n) {
                    counterMinus++;
                }
            }
        }
        if (counterPlus > 0 && counterMinus > 0) {
            for (int i = 0; i < mas.length ; i++) {
                for (int j = 0; j < mas.length ; j++) {
                    System.out.print(mas[i][j] + " ");
                }
                System.out.print("\n");
            }
        }
        else {
            mas = step1(n);
        }
        return mas;

    }
    private static int step2(int[][] mas) {
        int sum = 0;
        for (int i = 0; i < mas.length ; i++) {
            int counterOfPlus = 0;
            int inSum = 0;
            for (int j = 0; j < mas.length ; j++) {
                if (mas[i][j] > 0 && counterOfPlus == 1) {
                    sum +=inSum;
                    break;
                }
                if (mas[i][j] > 0 && counterOfPlus == 0) {
                    counterOfPlus++;
                }
                if (mas[i][j] < 0 && counterOfPlus ==1) {
                    inSum += mas[i][j];
                }
            }
        }
        System.out.println(sum);
        return sum;

    }
    private static int[][] step3(int[][] mas) {
        int max = mas[0][0];
        int posI = 0;
        int posJ = 0;
        int secondPosI = 0;
        int secondPosJ = 0;
        int delI;
        int delJ;
        for (int i = 0; i < mas.length ; i++) {
            for (int j = 0; j < mas.length ; j++) {
                if (mas[i][j] > max) {
                    max = mas[i][j];
                    posI = i;
                    posJ = j;
                }
            }
        }
        for (int i = 0; i < mas.length ; i++) {
            for (int j = 0; j <mas.length ; j++) {
                if (mas[i][j] == max && (posI !=i || posJ !=j)) {
                    secondPosI = i;
                    secondPosJ = j;
                }
            }
        }

        if (posI == secondPosI) {
            delI = 1;
        }
        else {
            delI = 2;
        }
        if (posJ == secondPosJ) {
            delJ = 1;
        }
        else {
            delJ = 2;
        }

        int[][] outPut = new int[mas.length - delI][mas.length - delJ];
        ArrayList<Integer> laying = new ArrayList<>();
        for (int i = 0; i < mas.length ; i++) {
            if (i == posI || i == secondPosI) {
                continue;
        }
            for (int j = 0; j < mas.length ; j++) {
                if (j == posJ || j == secondPosJ) {
                    continue;
                }
                System.out.print(mas[i][j] + " ");
                laying.add(mas[i][j]);
            }
            System.out.print("\n");
        }
        for (int i = 0; i < outPut.length; i++) {
            for (int j = 0; j <outPut[0].length ; j++) {
                outPut[i][j] = laying.get(i * outPut[0].length + j);
            }
        }
        return outPut;

    }
}
