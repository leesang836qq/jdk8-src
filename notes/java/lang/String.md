# String

[String](/src/java/lang/String.java)

所有String对象都是常量(理解java中的字符串常量请查看[StringTest](/test/test/java/lang/StringTest.java))，它们的值是不可变的。如果要使用可变的字符串，可以使用String buffers类型（StringBuffer StringBuilder）。

```java
/** The value is used for character storage. */
    private final char value[];
```

空构造：

```java
public String() {
    this.value = "".value;
}
```

常用构造：

```java
    public String(String original) {
        this.value = original.value;
        this.hash = original.hash;
    }
    public String(char value[]) {
        this.value = Arrays.copyOf(value, value.length);
    }
```

构造函数：

![String_Contructure](/notes/imgs/String_Contructure.png)

```java
public boolean equals(Object anObject) {
    if (this == anObject) {
        return true;
    }
    if (anObject instanceof String) {
        String anotherString = (String)anObject;
        int n = value.length;
        if (n == anotherString.value.length) {
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            while (n-- != 0) {
                if (v1[i] != v2[i])
                    return false;
                i++;
            }
            return true;
        }
    }
    return false;
}
```