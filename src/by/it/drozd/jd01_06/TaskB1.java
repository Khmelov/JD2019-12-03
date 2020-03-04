package by.it.drozd.jd01_06;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskB1 {
    public static void main(String[] args) {
        Pattern p=Pattern.compile("[а-яА-яёЁ]{1,}");
        StringBuilder sb=new StringBuilder(Poem.text);
        Matcher matcher=p.matcher(sb);
        while(matcher.find()){
            String word=matcher.group();
            findWords(word);
        }
    }
    private static void findWords(String word){
        if(word.length()>1){
            char last=word.toLowerCase().charAt(word.length()-1);
            char first=word.toLowerCase().charAt(0);
            if((first=='ц'||first=='к'||first=='н'||first=='г'||first=='ш'||first=='щ' || first=='з'||first=='х'||
                    first=='ф'||first=='в'||first=='п'||first=='р'||first=='л'||first=='д'||first=='ж'||
                        first=='ч'||first=='с'||first=='м'||first=='т'||first=='б'||last=='й')
                    &&(last=='у'||last=='е'||last=='ы'|| last=='а'||last=='о'||last=='э'||
                    last=='я'||last=='и'||last=='ю'||last=='ё')) {
                System.out.println(word+" ");
            }
        }
    }

}



