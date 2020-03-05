package by.it.cherkas.jd01_04;

import java.util.Scanner;

import static by.it.cherkas.jd01_04.InOut.printArray;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class TaskC {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String line;
        line = sc.nextLine();
        buiilOneDimArray(line);
    }

    static void buiilOneDimArray(String line) {
        double[] result;
        double firstElement, lastElement;
        String[] lineArr;
        lineArr = line.trim().split(" ");
        result = new double[lineArr.length];
        for (int i = 0; i < lineArr.length; i++) {
            result[i] = Double.parseDouble(lineArr[i]);
        }
        printArray(result, "V", 5);
        firstElement = result[0];
        lastElement = result[result.length-1];
        mergeSort(result);
        printArray(result, "V", 4);
        System.out.println("Index of first element=" + binarySearch(result, firstElement));
        System.out.println("Index of last element=" + binarySearch(result, firstElement));
    }
        private static int binarySearch(double[] arr, double elem) {
        int left, right, midd;
        left = 0;
        right = arr.length - 1;
        while (left<=right) {
            midd = (left + right)/2;
            if (arr[midd] == elem)
                return midd;
            else if (arr[midd]>elem)
                right = midd - 1;
            else left = midd + 1;
        }
        return -1;

        }

        static void printArray(double[] arr, String name, int columnCount) {
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("%s[%d]=%1.3f ", name, i, arr[i]);
                if ((i+1) % columnCount == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
        static void mergeSort(double[] array) {
        mergeSort(array, 0, array.length-1);
        }

        static void mergeSort(double[] array, int left, int right) {
        if (array.length == 0)
            return;
        if (left>right)
            return;
        double opora = array[(left + (right - left) / 2)];
        int i = left, j = right;
        while (i<=j) {
            while (array[i] < opora) {
                i++;
            }
            while (array[j] > opora) {
                j--;
            }
            if (i<=j) {
                double temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (left < j) mergeSort(array, left, j);
        if (right > i) mergeSort(array, i, right);
        }
}
