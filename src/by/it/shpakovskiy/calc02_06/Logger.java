package by.it.shpakovskiy.calc02_06;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;

class Logger {
    private static volatile Logger instance;
    private int count;
    Path path = Paths.get(System.getProperty("user.dir") + "/src/by/it/shpakovskiy/calc02_06/log.txt");

    private Logger() {
        try {
            count = (int) Files.lines(path).count();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static Logger getInstance(){
        Logger localInstance = instance;
        if (localInstance == null) {
            synchronized (Logger.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = new Logger();
                    instance = localInstance;
                }
            }
        }
        return localInstance;
    }

    void print(String message) {
        if (count >= 50) {
            clear();
        }
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, ResMan.getLocale());
        if (message != null) {
            if (message.equals("end")) {
                message += "\n" + ResMan.get("iHopeToSeeYouAgain") + " \u270B";
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(path.toFile(), true))) {
                count++;
                String date = df.format(new Date());
                writer.print(date);
                writer.print(" - ");
                writer.print(message);
                writer.println();
                writer.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void clear() {
        count = 0;
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
