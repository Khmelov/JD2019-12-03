package by.it.busel.calc02_06;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

/**
 * a class that operates activities concerning a process of events logging in a calculator
 */
public class LoggerSingleton {

    /**
     * a unique instance of LoggerSingleton class
     */
    private static volatile LoggerSingleton logger;

    /**
     * a singleton initializer method, i.e. the only method to initialize "logger".
     *
     * @return a unique instance of LoggerSingleton class "logger"
     */
    public static LoggerSingleton getInstance() {
        LoggerSingleton localLogger = logger;
        if (localLogger == null) {
            synchronized (LoggerSingleton.class) {
                localLogger = logger;
                if (localLogger == null) {
                    logger = localLogger = new LoggerSingleton();
                }
            }
        }
        return localLogger;
    }

    /**
     * a list which contains logs while "ConsoleRunner.java" is executing
     */
    private static LinkedList<String> logJournal = new LinkedList<>();

    /**
     * a String that contains a Path for a file, where logs are stored,
     * when "ConsoleRunner.java" isn't executing, and where
     * new logs are saved
     */
    private static final String LOG_FILE_PATH =
            FileAssistant.generateFilePath("log.txt", LoggerSingleton.class);

    /**
     * a String that represent an element, which separates logs when they are to be
     * written in a file
     */
    private static final String LOG_SEPARATOR = "___________________\n";
    /*
     * JavaDoc can't be formed for static logic blocks
     */

    /**
     * a static object, which defines an unchanging format of Date objects that contains present time.
     * <p>
     * "dateFormat" is used while logging
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    static {
        try {
            logJournalReconstructionFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method which checks whether a target file with "LOG_FILE_PATH" is created,
     * if it is this method this method loads logs to "logJournal",
     * or just creates this file.
     * Moreover, the annotation "SuppressWarnings" is used in order to suppress
     * an IDEA's warning about non-usage of the value "file.createNewFile()"
     *
     * @throws IOException while reading IOException can be thrown
     */
    @SuppressWarnings("all")
    private static void logJournalReconstructionFromFile() throws IOException {
        File file = new File(LOG_FILE_PATH);
        if (file.exists())
            try (BufferedReader reader = new BufferedReader(new FileReader(file))
            ) {
                String logFileContents = readContentsOfFile(reader);
                String[] logFromFile = logFileContents.split(LOG_SEPARATOR);
                logJournal.addAll(Arrays.asList(logFromFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        else file.createNewFile();
    }

    /**
     * a method that returns contents of a target file with "LOG_FILE_PATH"
     *
     * @param reader a tool which reads contents of an aforementioned file
     * @return a String representation of all contents of an aforementioned file
     * @throws IOException while reading IOException can be thrown
     */
    private static String readContentsOfFile(BufferedReader reader) throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line).append('\n');
        }
        return builder.toString();
    }

    /**
     * a method that saves a log and rewrite an current state of "logJournal" to a target file
     *
     * @param message output from a console
     * @param cause   input to a console
     */
    static void save(String message, String cause) {
        String log = formLog(message, cause);
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_PATH))) {
            if (logJournal.size() >= 50) {
                logJournal.removeFirst();
            }
            logJournal.addLast(log);
            writer.write(logJournalToString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method that forms log on the basis of input and output of a console.
     * <p>
     * A contamination of String objects (instead of StringBuilder) is used
     * because constant parts of each log are taken from "a String-pool".
     *
     * @param message output from a console
     * @param cause   input to a console
     * @return one separate log that is formed on the basis of input and output of a console
     */
    private static String formLog(String message, String cause) {
        return dateFormat.format(new Date()) + '\n' + ResourcesManager.get(Message.PRINTER_INPUT) +
                '\n' + cause + '\n' + ResourcesManager.get(Message.PRINTER_OUTPUT) +
                '\n' + message + '\n';
    }

    /**
     * a method that forms a "logJournal" to String in order to write it in a target file
     *
     * @return a String representation of contents of "logJournal"
     */
    private static String logJournalToString() {
        StringBuilder sb = new StringBuilder();
        for (String log : logJournal) {
            sb.append(log).append(LOG_SEPARATOR);
        }
        return sb.toString();
    }
}
