package by.it.lozouski.jd01_14;

import java.io.File;

public class Helper {

    public static String getPath(String fileName, Class<?> yourClass) {
        String dir = System.getProperty("user.dir");
        String packageName = yourClass.getName().replace(yourClass.getSimpleName(), "");
        String path = dir + File.separator + "src" + File.separator + packageName.replace(".", File.separator);
        fileName = path + fileName;
        return fileName;
    }

    public static String getPathPackage(Class<?> yourClass) {
        String dir = System.getProperty("user.dir");
        String packageClassName = dir + File.separator + "src" + File.separator + yourClass.getPackage().getName().replace(".", File.separator) + File.separator;
        final File path = new File(packageClassName);
        return path.getParent();
    }

    public static String getPackage(Class<?> yourClass) {
        String dir = System.getProperty("user.dir");
        return dir + File.separator + "src" + File.separator + yourClass.getPackage().getName().replace(".", File.separator);
    }
}
