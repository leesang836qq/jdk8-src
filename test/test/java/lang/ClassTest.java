package test.java.lang;

import org.junit.Test;
import org.zwobble.mammoth.DocumentConverter;

import java.io.File;
import java.io.IOException;
import java.time.format.SignStyle;
import java.util.HashMap;

/**
 * Created by two8g on 16-9-18.
 */
public class ClassTest {
    /**
     * result:
     * class test.ClassTest
     * public class test.ClassTest
     * int
     * int
     * class java.lang.Boolean
     * public final class java.lang.Boolean
     * class java.lang.Void
     * public final class java.lang.Void
     * class java.time.format.SignStyle
     * public final enum java.time.format.SignStyle
     * interface org.junit.Test
     * public abstract @interface org.junit.Test
     * class java.util.HashMap
     * public class java.util.HashMap<K,V>
     * class java.util.HashMap
     * public class java.util.HashMap<K,V>
     */
    @Test
    public void testToGenericString() {
        System.out.println(ClassTest.class.toString());
        System.out.println(ClassTest.class.toGenericString());
        System.out.println(int.class.toString());
        System.out.println(int.class.toGenericString());
        System.out.println(Boolean.class.toString());
        System.out.println(Boolean.class.toGenericString());
        System.out.println(Void.class.toString());
        System.out.println(Void.class.toGenericString());
        System.out.println(SignStyle.class.toString());
        System.out.println(SignStyle.class.toGenericString());
        System.out.println(Test.class.toString());
        System.out.println(Test.class.toGenericString());
        System.out.println(HashMap.class.toString());
        System.out.println(HashMap.class.toGenericString());
        HashMap<String, String> hashMap = new HashMap<String, String>();
        System.out.println(hashMap.getClass().toString());
        System.out.println(hashMap.getClass().toGenericString());
    }

    @Test
    public void testIsPrim() {
        System.out.println(boolean.class.isPrimitive());
        System.out.println(Boolean.class.isPrimitive());
    }

    @Test
    public void minMaxInt() {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE - 1);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.MAX_VALUE * 3);
    }

    @Test
    public void floatTest() {
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(0f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(1f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(0.5f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(0.75f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(0.1f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(1.1f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(-0f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(-1f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(-0.5f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(-0.75f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(-0.1f)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(-1.1f)));
    }

    @Test
    public void page() {
        for (int i = 1; i < 30; i++) {
            assert ((i - 1) / 10 + 1) == (i % 10 == 0 ? i / 10 : i / 10 + 1);
        }
    }

    @Test
    public void convert() throws IOException {
        DocumentConverter converter = new DocumentConverter();
        System.out.println(converter.convertToHtml(new File("/home/two8g/Develop/IdeaProjects/dxhpaper/docxs/80600163.docx")).getValue());
    }
}
