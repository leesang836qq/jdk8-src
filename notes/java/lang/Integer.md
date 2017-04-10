# java.lang.Integer

[java.lang.Integer](/src/java/lang/Integer.java)

Integer类的实例封装了一个原始类型int的值，Integer对象中包含一个单独的int类型域`private final int value;`,。

此外，还提供了多种int和String互相转换的方法，还有其它实用的处理int的常量和方法。

```java
public final class Integer extends Number implements Comparable<Integer>{}
```
`Integer`是final class，所以它的实例是不可变对象，继承[`Number`](Number.md)，并实现了[`Comparable<>`](Comparable.md)接口。

```java
@Native public static final int   MIN_VALUE = 0x80000000;
@Native public static final int   MAX_VALUE = 0x7fffffff;
```

Integer用于4个字节数据。最大值、最小值是$2^31-1$、$-2^31$, 这两个值已经在类中定义为常量.

获取Integer的Class对象使用

```java
public static final Class<Integer>  TYPE = (Class<Integer>) Class.getPrimitiveClass("int");
```

`Integer`拥有两个构造函数：

```
public Integer(int value) {
    this.value = value;
}

public Integer(String s) throws NumberFormatException {
    this.value = parseInt(s, 10);
}
```

一个是通过一个int数值封装成一个Integer对象，一个是通过十进制数值字符串构造一个Integer对象，字符串如果不是合法十进制数值字符串将抛出`NumberFormatException`异常。具体操作都是将int值赋值给内部int域`private final int value;`。

Integer还提供另外三种构造Integer对象的static方法：

```java
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
public static Integer valueOf(String s) throws NumberFormatException {
    return Integer.valueOf(parseInt(s, 10));
}
public static Integer valueOf(String s, int radix) throws NumberFormatException {
    return Integer.valueOf(parseInt(s,radix));
}
```

特别需要注意的是Integer类中的缓存, 对于`[-128,127]`的相同整型数值，通过`valueOf`方法(而`new Integer(i)`不是)构造的Integer对象都是同一个对象。具体代码如下：

```
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

```java
public Integer(int value) {
    this.value = value;
}
```

```java
private static class IntegerCache {
    static final int low = -128;
    static final int high;
    static final Integer cache[];

    static {
        // high value may be configured by property
        int h = 127;
        String integerCacheHighPropValue =
            sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        if (integerCacheHighPropValue != null) {
            try {
                int i = parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
            } catch( NumberFormatException nfe) {
                // If the property cannot be parsed into an int, ignore it.
            }
        }
        high = h;

        cache = new Integer[(high - low) + 1];
        int j = low;
        for(int k = 0; k < cache.length; k++)
            cache[k] = new Integer(j++);

        // range [-128, 127] must be interned (JLS7 5.1.7)
        assert IntegerCache.high >= 127;
    }

    private IntegerCache() {}
}
```


```java
public static int hashCode(int value) {
    return value;
}
```

Integer/int对象的hashcode就是int的值。对于null的Integer对象是0.