package test.java.lang;

import org.junit.Test;

/**
 * Created by two8g on 17-3-27.
 */
public class StringTest {
    private static final String STR1 = "str";
    private static final String STR2 = "ing";

    public static final String A; // 常量A
    public static final String B; // 常量B

    static {
        A = "str";
        B = "ing";
    }

    @Test
    //http://www.jianshu.com/p/c7f47de2ee80
    //http://rednaxelafx.iteye.com/blog/774673
    public void finalTest() {
        String str1 = "str";
        String str2 = "ing";

        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        System.out.println(str3 == str4);//false
        String str4_1 = str4.intern();
        System.out.println(str3 == str4_1);//true
        System.out.println(str3 == str4);//false

        String str5 = "string";
        System.out.println(str3 == str5);//true

        String str6 = STR1 + STR2;
        System.out.println(str3 == str6);//true
        //编译器,STR1 STR2已经确定,在常量池中

        String str7 = A + B;
        System.out.println(str3 == str7);
        //false
        //A和B虽然被定义为常量，但是它们都没有马上被赋值。
        // 在运算出s的值之前，他们何时被赋值，以及被赋予什么样的值，都是个变数。
        // 因此A和B在被赋值之前，性质类似于一个变量。那么s就不能在编译期被确定，而只能在运行时被创建了。
    }
}
