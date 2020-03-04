package by.it.busel.calc02_06;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * a class that operates activities concerning a process of events logging in a calculator.
 * Beginning from JD02_06 it became deprecated because of statements of tasks JD02_06.
 */
@Deprecated
class Logger {
    /**
     * a list which contains logs while "ConsoleRunner.java" is executing
     */
    @Deprecated
    private static LinkedList<String> logJournal = new LinkedList<>();

    /**
     * a String that contains a Path for a file, where logs are stored,
     * when "ConsoleRunner.java" isn't executing, and where
     * new logs are saved
     */
    @Deprecated
    private static final String LOG_FILE_PATH = FileAssistant.generateFilePath("log.txt", Logger.class);

    /**
     * a String that represent an element, which separates logs when they are to be
     * written in a file
     */
    @Deprecated
    private static final String LOG_SEPARATOR = "___________________\n";

    /*
     * JavaDoc can't be formed for static logic blocks
     */
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
    @Deprecated
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
     * @param log a newly formed log that is to be added to "logJournal"
     */
    @Deprecated
    static void save(String log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_PATH))) {
            if (logJournal.size() >= 50) {
                logJournal.removeFirst();
                logJournal.addLast(log);
            } else {
                logJournal.addLast(log);
            }
            writer.write(logJournalToString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method that forms a "logJournal" to String in order to write it in a target file
     *
     * @return a String representation of contents of "logJournal"
     */
    @Deprecated
    private static String logJournalToString() {
        StringBuilder sb = new StringBuilder();
        for (String log : logJournal) {
            sb.append(log).append(LOG_SEPARATOR);
        }
        return sb.toString();
    }
}
