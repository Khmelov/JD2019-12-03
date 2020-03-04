package by.it.shpakovskiy.jd02_06;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class TestLogger {
    String message = "What the f***!?";
    @Test
    public void getInstance() {
        Logger logger=Logger.getInstance();
        Logger logger1=Logger.getInstance();
        if (logger!=logger1) {
            fail(message);
        }
    }

    @Test
    public void print() {
        Logger logger = Logger.getInstance();
        String s1 = "Hello";
        String s2 = "How are you?";
        String s3 = "What are you doing?";
        logger.print(s1);
        logger.print(s2);
        logger.print(s3);
        try {
            List<String> list = Files.readAllLines(logger.getFile().toPath());
            int size = list.size();
            if (size<3) fail(message);
            if (!list.get(size-3).endsWith(s1)) fail(message);
            if (!list.get(size-2).endsWith(s2)) fail(message);
            if (!list.get(size-1).endsWith(s3)) fail(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}