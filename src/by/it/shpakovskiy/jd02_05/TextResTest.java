package by.it.shpakovskiy.jd02_05;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class TextResTest {

    @Test
    public void testWelcomeMessage() {
        TextRes.changeLanguage(Locale.US);
        String str = TextRes.get(TextRes.text.WELCOME);
        assertEquals("Welcome!", str);
        TextRes.changeLanguage(new Locale("ru", "RU"));
        str = TextRes.get(TextRes.text.WELCOME);
        assertEquals("Добро пожаловать!", str);
        TextRes.changeLanguage(new Locale("be", "BY"));
        str = TextRes.get(TextRes.text.WELCOME);
        assertEquals("Сардэчна запрашаем!", str);
    }

    @Test
    public void testGetUserName() {
        TextRes.changeLanguage(Locale.ENGLISH);
        String str = TextRes.get(TextRes.user.NAME);
        assertEquals("Ilya", str);
        TextRes.changeLanguage(new Locale("be", "BY"));
        str = TextRes.get(TextRes.user.NAME);
        assertEquals("Ілья", str);
    }
}