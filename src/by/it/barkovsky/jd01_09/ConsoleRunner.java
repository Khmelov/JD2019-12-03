package by.it.barkovsky.jd01_09;

import java.util.Scanner;

public class ConsoleRunner {

    public static void main(String[] args) {
        String line;
        Scanner scan = new Scanner(System.in);

        Parser parser = new Parser();
        Printer printer = new Printer();
        while(!(line=scan.nextLine()).equals("end")){
            Var result = parser.calc(line);
            printer.print(result);
        }
    }
}
