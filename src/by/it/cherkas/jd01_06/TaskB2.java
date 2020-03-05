package by.it.cherkas.jd01_06;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskB2 {

    public static void main(String[] args) {
        Pattern split = Pattern.compile("[/.]+");
        Pattern n = Pattern.compile("[-,!:\\n]");
        StringBuilder sbText = new StringBuilder(Poem.text);
        Matcher m = n.matcher(sbText);
        while (m.find()) {
            sbText.replace(m.start(), m.end(), " ");
        }

        String[] textArray = split.split(sbText);
        for (int i = 0; i < textArray.length ; i++) {
            textArray[i] = textArray[i].trim().replaceAll("\\s{2,}", " ");
        }
        for (int a = 1; a <textArray.length ; a++) {
            for (int b = textArray.length -1; b >= a; b--) {
                if (textArray[b-1].length() > textArray[b].length()){
                    String t = textArray[b-1];
                    textArray[b-1] = textArray[b];
                    textArray[b] = t;
                }
            }
        }
        StringBuilder newText = new StringBuilder();
        for (int i = 0, textArrayLenght = textArray.length; i < textArrayLenght; i++) {
            if (i==4) continue;
            newText.append(textArray[i]).append("\n");
        }
        System.out.println(newText);
    }
}
