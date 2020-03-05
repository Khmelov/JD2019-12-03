package by.it.shulga.jd01.jd01_10;

import java.lang.reflect.Method;

class BeanTester {
    public static void main(String[] args) throws Exception {
        Class<?> testingBean = by.it.shulga.jd01.jd01_10.Bean.class; //Get structure of the class Bean.
        Object objBean = testingBean.newInstance(); //This operation return object of the class Bean.
        Method[] declaredMethodsBean = testingBean.getDeclaredMethods(); //Get structure of the declared methods(Class Bean).
        for (Method elem : declaredMethodsBean) {
            if (elem.isAnnotationPresent(Param.class)) { // If annotation exists...
                Param annotationBean = elem.getAnnotation(Param.class); //Get annotation.
                elem.setAccessible(true); // If method is private, then you get access.
                //Call the method, and assign the result to a variable:
                double resultTest = (double) elem.invoke(objBean, annotationBean.a(), annotationBean.b());
                System.out.println(elem.getName() + " " + resultTest);//sout...
                elem.setAccessible(false);//Deny access.
            }
        }
    }
}
