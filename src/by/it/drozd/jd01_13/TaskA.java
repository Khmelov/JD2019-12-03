package by.it.drozd.jd01_13;

import java.util.HashMap;

public class TaskA {
    public static void main(String[] args) {
        try{
            if (Math.random()>0.5)
                new HashMap<String, String>(null);
            else
                Integer.parseInt("привет");
        }catch (Exception e){
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if(TaskA.class.getName().equals(stackTraceElement.getClassName())){
                    System.out.println(stackTraceElement);
                    String name=e.getClass().getName();
                    String className=stackTraceElement.getClassName();
                    int number=stackTraceElement.getLineNumber();
                    System.out.printf("name: %s\n class: %s\n line: %d\n",name,className,number);
                    break;
                }
            }
        }

    }
}
