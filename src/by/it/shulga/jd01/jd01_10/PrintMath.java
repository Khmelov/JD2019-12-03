package by.it.shulga.jd01.jd01_10;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

class PrintMath {
    public static void main(String[] args) {
        Class<Math> mathClass = Math.class;
        Method[] mathMethods = mathClass.getDeclaredMethods();
        StringBuilder myText = new StringBuilder();
        for (Method elem : mathMethods) {
            int modifiers = elem.getModifiers();
            if (Modifier.isPublic(modifiers)) {
                myText.append("public");
                if (Modifier.isStatic(modifiers)) {
                    myText.append(" static");
                    myText.append(" ").append(elem.getReturnType()).append(" ").append(elem.getName()).append("(");
                    Type[] genericParameterTypes = elem.getGenericParameterTypes();
                    if (genericParameterTypes.length != 0) {
                        for (int i = 0; i < genericParameterTypes.length; i++) {
                            if (i == genericParameterTypes.length - 1) {
                                myText.append(genericParameterTypes[i]).append(")").append('\n');
                            } else myText.append(genericParameterTypes[i]).append(",");
                        }
                    }else myText.append(")");
                }
            }
        }
        Field[] mathFields = mathClass.getFields();
        for (Field elem : mathFields) {
            myText.append(elem.getType().getName()).append(" ").append(elem.getName()).append('\n');
        }
        System.out.println(myText);
    }
}
