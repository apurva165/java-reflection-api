package reflection.methodsexample;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class BasicExample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        String personClassName = "reflection.methodsexample.Person";
        //reflection method forName
        Class<?> personClass = Class.forName("reflection.methodsexample.Person");

        System.out.println(personClass);

        // Returns public fields of class and it super class
        Field[] fields = personClass.getFields();
        System.out.println("Fields : ");
        System.out.println(Arrays.toString(fields));



        //Get all declared fields of an array
        Field[] declaredFields = personClass.getDeclaredFields();
        System.out.println("declared Fields : ");
        System.out.println(Arrays.toString(declaredFields));

        System.out.println("Field name and Value : " + declaredFields[0].getName() );

        //Get all methods of an array as well as extending class
        System.out.println("Methods");
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.toString());
        }
    }
}
