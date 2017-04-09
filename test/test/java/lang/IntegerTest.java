package test.java.lang;

import org.junit.Test;

/**
 * Created by two8g on 17-3-28.
 */
public class IntegerTest {
    @Test
    public void test() {
        int i1 = 1;
        Integer i2 = 1;
        Integer i3 = new Integer(1);
        Integer i4 = Integer.valueOf(1);

        System.out.println("i1 == i2?" + (i1 == i2));//true
        System.out.println(i1 == i3);//true
        System.out.println(i1 == i4);//true

        System.out.println(i2 == i3);//false
        System.out.println(i2 == i4);//true

        System.out.println(i2.equals(i3));//true
        System.out.println(i2.equals(i4));//true

        System.out.println(i3 == i4);//false
        System.out.println(i3.equals(i4));//true

        System.out.println("=========");

        int i128 = 128;
        Integer i129 = 128;
        Integer i130 = new Integer(128);
        Integer i131 = Integer.valueOf(128);

        System.out.println(i128 == i129);//true
        System.out.println(i128 == i130);//true
        System.out.println(i128 == i131);//true

        System.out.println(i129 == i130);//false
        System.out.println(i129 == i131);//false

        System.out.println(i129.equals(i130));//true
        System.out.println(i129.equals(i131));//true

        System.out.println(i130 == i131);//false

        System.out.println(i130.equals(i131));//true
    }
}
