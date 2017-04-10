# java.lang.Object

[java.lang.Object.java](/src/java/lang/Object.java)

`Object`类是所有类层级的根,包括数组.

![Object_Structure.png](/notes/imgs/Object_Structure.png)

方法`m`上有钉子样式的表示是[`final`](#final关键字)的。`final`表示不能重写的。

主要定义的方法有`getClass`、`hashCode`、`equals`、`clone`,`toString`:

```java
public class Object{
    public final native Class<?> getClass();
    public native int hashCode();
    public boolean equals(Object obj) {
            return (this == obj);
        }
    protected native Object clone() throws CloneNotSupportedException;
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
}
```

其中`equals`,`toString`都通过java代码提供实现.而`hashCode`、`getClass`、`clone`是[native](#native方法)（知识点啊）,即由平台相关代码实现,其中`getClass`是`final`的.



下面依次讨论上面5个方法.

## 1.getClass方法

`getClass`方法定义如下

```java
public final native Class<?> getClass();
```

该方法获取的是Obejct的运行时的class,返回的一个Class对象,如何一个Obejct都关联一个`java.lang.Class`的实例的引用.
另外注意`Class<?>`的形式.实际表示是`Class<? extends |X|>`,其实.
其中`|X|`是对象的static type()的类型擦除结果.

这里设计两个概念*static type*和*类型擦除*. 先说*static type*,

*static
type*字面理解即静态类型,那上面是静态类型呢?既然有静态类型,那是不是还有动态类型呢?

首先要指出,*static type*确切的说是*compile-time type*指编译时类型.而*dynamic
type*(动态类型)是*runtime type*指运行时类型.
注意千万不要和静态类型语言和动态类型语言概念(两种语言分类)混淆.所以我们改用*compile-time
type*和*runtime type*进行说明,避免混淆. 不过这里的*static
type*与*静态类型语言*也有点关系. (我对这理解还不够深入,大家最好看看权威文档.
我主要参考了这个[讨论](http://stackoverflow.com/questions/14963943/what-is-the-difference-between-a-compile-time-type-vs-run-time-type-for-any-obje))

>> Java is a statically typed language, so the compiler will attempt to
>> determine the types of everything and make sure that everything is
>> type safe. Unfortunately static type inference is inherently limited.
>> The compiler has to be conservative, and is also unable to see
>> runtime information. Therefore, it will be unable to prove that
>> certain code is typesafe, even if it really is.

意思是说,JAVA是静态类型语言.所以编译器需要尝试确认所有东西的的类型来保证类型安全.但是,静态类型语言的推到是有一定限制的.编译器采取保守手段,
它无法获得运行时信息. 所以, 编译器无法保证代码的类型安全, 即使代码确实是安全的.

>> The run time type refers to the actual type of the variable at
>> runtime. As the programmer, you hopefully know this better than the
>> compiler, so you can suppress warnings when you know that it is safe
>> to do so.

运行时类型是指,代码时间运行时的类型.
作为程序员,你希望比编译器更好的清除代码,所以,当你知道代码是类型安全的时候你可以屏蔽警告.

我的理解是. *compile-time type*就是我们声明参数或变量时的类型.而*runtime
type*就是实际运行时对象的真实类型.
而getClass方法返回的参数类型的只能是*compile-time type*.
因为前面说的,编译器获取不到运行时信息. 所以在使用getClass方法时,这样写是正确的:

```java
List<Integer> integerList = new ArrayList<>();
Class<? extends List> integerListClass = integerList.getClass();
ArrayList<Integer> integerList_1 = new ArrayList<>();
Class<? extends ArrayList> integerListClass_1 = integerList_1.getClass();
```

而下面这样的写法,是无法编译通过的.

```java
List<Integer> integerList = new ArrayList<>();
Class<? extends ArrayList> integerListClass = integerList.getClass();
```

理解了上面的静态类型和动态类型就非常容易理解JAVA面向对象的多态特性,以及类型转换知识.

然后说说*类型擦除*.*类型擦除*是JAVA泛型支持特性的知识.

>> Java泛型（generics）是JDK
>> 5中引入的一个新特性，允许在定义类和接口的时候使用类型参数（type
>> parameter）。声明的类型参数在使用时用具体的类型来替换。泛型最主要的应用是在JDK
>> 5中的新集合类框架中.

而对于类型擦除,例如: List<String> 的类型擦除类型为 List.

以下代码是正确的:

```java
Object o = new Object();
Class oClass = o.getClass();
String s = "str";
Class sClass = s.getClass();
List<Integer> integerList = new ArrayList<>();
Class<? extends List> integerListClass = integerList.getClass();
ArrayList<Integer> integerList_1 = new ArrayList<>();
Class<? extends ArrayList> integerListClass_1 = integerList_1.getClass();
```

理解了静态类型/动态类型和类型擦除,以下代码就很容易明白了

```
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
```

## 2.hashCode方法

都明白这个方法返回的是对象的hash code.
hash值是用于各种hash表中的，好的hashCode方法对hash表的性能有很大作用。最常用的hash表类型是HashMap.

hash算法三条约束：

- 不变性约束
- 相等性约束
- 不等性约束(非强制)

不变性约束：任何时候对同一个对象调用hash方法返回值是不变的。
相等性约束：与equals方法有关，如果两个对象满足equals返回值是true，那么它们的hash值也必修相等。
不等性约束：即如果两个对象不是equals的，那它们的hash值应该是不相等的。文档中说这条约束不是强制的。

Object类的hashCode返回的是对象的内部地址，由平台相关代码实现，不依赖Java语言。

已知Java中String类的hashCode算法为：

![String.hashCode](/notes/imgs/String_hashCode.svg)

## 3.equals方法

equals必须满足五条法则：

- reflexive(自反性)
- symmetric(对称性)
- transitive(传递性)
- consistent(一致性)
- null-false(空不等性)

自反性： 任何非null的x, `x.equals(x)`为true; 对称性： 对任何非null的x, y,
`x.equals(y)`为true，都有`y.equals(x)`为true; 传递性： 对任何非null的x, y, z,
如果`x.equals(y)`, `y.equals(z)`为true，有`x.equals(z)`为true; 一致性：
对任何非null的x, y, 多次调用`x.equals(y)`的返回值一致; 空不等： 任何非null的x,
`x.equals(null)`为false.

另外注意，`equals(Object obj)`方法中参数都是以Object对象传递。

<!--错误的equals方法写法：-->

<!--```java-->

<!--public Class A {-->

    <!--@Override-->
    <!--public boolean equals(Object a) {-->
        <!--return (this == a);-->
    <!--}-->

<!--}-->

<!--```-->

## 4.clone方法

定义clone()的目的是为了获得对象的一个拷贝。clone方法更确切的含义可能要看对象的类型是什么。
一般目的是，`x.clone() != x`(即克隆对象与原型对象不是同一个对象),`x.clone().getClass() ==
x.getClass()`(即克隆对象与原型对象的类型一样)都为true. 更典型的结果是`x.clone().equals(x)`为true.
但这些都不是强制的。
通常来说，clone返回的对应应该独立于原对象。为此，一般在调用super.clone返回前，修改部分字段。

需要使用clone方法，类必需实现Cloneable接口。否则，编译器会直接返回CloneNotSupportedException.例如：

```java
class ConcretePrototype implements Cloneable
{
    public Prototype clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch	(CloneNotSupportedException	exception) {
            System.err.println("Not	support	cloneable");
        }
        return (Prototype)object;
    }
}
```

对象拷贝包括“深克隆”和“浅克隆”。数据类型分为值类型(基本数据类型)和引用类型。
浅克隆和深克隆的主要区别在于是否支持引用类型的成员变量的复制。
对于基本类型，拷贝的就是基本类型的值。
“浅克隆”，只对对象中的基本类型进行复制。
而“深克隆”会对其包含的引用类型进行复制。
参考(https://quanke.gitbooks.io/design-pattern-java/content/%E5%AF%B9%E8%B1%A1%E7%9A%84%E5%85%8B%E9%9A%86%E2%80%94%E2%80%94%E5%8E%9F%E5%9E%8B%E6%A8%A1%E5%BC%8F%EF%BC%88%E4%B8%89%EF%BC%89.html)

在浅克隆中,如果原型对象的成员变量是值类型,将复制一份给克隆对象;如果原型对象的
成员变量是引用类型,则将引用对象的地址复制一份给克隆对象,也就是说原型对象和克隆
对象的成员变量指向相同的内存地址。简单来说,在浅克隆中,当对象被复制时只复制它本
身和其中包含的值类型的成员变量,而引用类型的成员对象并没有复制

![ShallowClone](/notes/imgs/ShallowClone.gif)

在深克隆中，无论原型对象的成员变量是值类型还是引用类型，都将复制一份给克隆对象，深克隆将原型对象的所有引用对象也复制一份给克隆对象。简单来说，在深克隆中，除了对象本身被复制外，对象所包含的所有成员变量也将复制，如图7-6所示：

![DeepClone](/notes/imgs/DeepClone.gif)

注意，所有Array数组类型T[]，都单独实现了clone方法。不管T类型是引用类型或者基本类型。而对应T是引用类型时，clone方法采用的是“浅克隆”的方式。

设计模式中的“原型模式”需要用到上述所说知识。

## 5.toString

toString方法是我们经常使用的。Object类提供默认toString的实现为`getClass().getName() + "@" + Integer.toHexString(hashCode())`. 其中getClass().getName()请查看[Class](Class.md).
如果类如果没有覆盖改方法，那个对象调用toString方法返回的是“类名字串@地址hash字串”。

### native方法

别人都解释“native”是“本地的”的意思，我查了字典，发现native还有个意思“与生俱来的/原生”在这更合适。
Java中的“native方法”解释成java中“与生俱来(/原生)的方法”不是更好理解吗？Java语言是一种高级语言，也是由C开发而来的，所以“native”方法底层是就是使用C的，伴随Java语言一起产生的。native关键字来源与JNI。

关于JNI的文档：

http://docs.oracle.com/javase/8/docs/technotes/guides/jni/
https://en.wikipedia.org/wiki/Java_Native_Interface
https://dirtysalt.github.io/jni.html

>> Java Native Interface (JNI) is a standard programming interface for writing Java native methods and embedding the Java virtual machine into native applications. The primary goal is binary compatibility of native method libraries across all Java virtual machine implementations on a given platform.

### final关键字



### 相关链接

1. http://www.cnblogs.com/cq-home/p/6431426.html