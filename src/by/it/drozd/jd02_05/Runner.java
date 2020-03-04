package by.it.drozd.jd02_05;

import java.util.Locale;

public class Runner {
    public static void main(String[] args) {
        ResMan res=ResMan.INSTANCE;
        if (args.length==2){
            Locale locale=new Locale(args[0],args[1]);
            res.setLocale(locale);
        }
        System.out.println(res.get(Messages.WELCOME));
        System.out.println(res.get(User.FIRST_NAME));
        System.out.println(res.get(User.LAST_NAME));
        System.out.println(res.get(Messages.QUESTION));
    }
}
