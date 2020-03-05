package by.it.cherkas.jd01_04;



import java.util.Scanner;

public class TaskB {
    public static void main(String[] args) {
        System.out.println("Введите число людей");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] surname = new String[n];
        System.out.println("Введите фамилии");
        for (int i = 0; i <surname.length ; i++) {
            surname[i]=sc.next();
        }

        {
            int[][] salary = new int[n][4];
            for (int i = 0; i <n; i++) {
                System.out.println("Введите зп для "+ surname[i]);
                for (int j = 0; j < 4; j++) {
                    salary[i][j]=sc.nextInt();


                }
            }
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.printf("%-25s %-11s %-11s %-11s %-11s %-25s %n", "Фамилия", "Квартал1", "Квартал2", "Квартал3", "Квартал4", "Итого");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            double total = 0;
            double middle = 0;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 4; j++) {
                    sum = sum + salary[i][j];
                }
                total =sum;
                System.out.printf("%-25s %-11s %-11s %-11s %-11s %-25s %n", surname[i], salary[i][0], salary[i][1], salary[i][2], salary[i][3], sum);

            }
            System.out.println("---------------------------------------------------------------------------------------------------------");
            middle=total/(n*4);
            System.out.printf("%-25s %-10s", "Итого ", total);
            System.out.println();
            System.out.printf("%-25s %-10s", "Средняя ", middle);
        }

    }
}