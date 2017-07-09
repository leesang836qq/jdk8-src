package test.java.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by two8g on 17-3-29.
 */
public class ObjectTest {
    @Test
    public void getClassTest() {
        Object o = new Object();
        Class oClass = o.getClass();
        String s = "str";
        Class sClass = s.getClass();
        List<Integer> integerList = new ArrayList<>();
        Class<? extends List> integerListClass = integerList.getClass();
        System.out.println(integerListClass.toString());
        ArrayList<Integer> integerList_1 = new ArrayList<>();
        Class<? extends ArrayList> integerListClass_1 = integerList_1.getClass();
        System.out.println(integerListClass_1.toString());
    }

    @Test
    public void getClassEqualTest() {
        Object oa = new Object();
        Object ob = new Object();
        Class oaClass = oa.getClass();
        Class obClass = ob.getClass();
        System.out.println(oaClass == obClass); //true

        List<Integer> integerList = new ArrayList<>();
        Class<? extends List> integerListClass = integerList.getClass();
        ArrayList<Integer> integerList_1 = new ArrayList<>();
        Class<? extends ArrayList> integerListClass_1 = integerList_1.getClass();

        List<Integer> integerList_2 = new LinkedList<>();
        Class<? extends List> integerListClass_2 = integerList_2.getClass();

        List<String> stringList_3 = new ArrayList<>();
        Class<? extends List> stringListClass_3 = stringList_3.getClass();

        System.out.println(integerListClass == integerListClass_1); //true
        System.out.println(integerListClass == integerListClass_2); //false
        System.out.println(integerListClass == stringListClass_3); //true

        System.out.println(integerListClass); // class java.util.ArrayList
        System.out.println(integerListClass_2);// class java.util.LinkedList
        System.out.println(stringListClass_3);// class java.util.ArrayList
    }

    @Test
    public void toStringTest() {
        int[] ints = new int[0];
        ObjectTest objectTest = new ObjectTest();
        System.out.println(ints.toString());
        System.out.println(objectTest.toString());
        //[I@64a294a6
        //test.java.lang.ObjectTest@7e0b37bc
    }
}
