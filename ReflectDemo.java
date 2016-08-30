package com.fahai;

import org.junit.Test;

import java.lang.reflect.*;

/**
 * Created by zhanghao on 16/8/30.
 */
public class ReflectDemo {
    //通过JAVA反射机制得到类的包名和类名
    @Test
    public void test1(){
        Person person = new Person();
        System.out.println("包名：" + person.getClass().getPackage().getName() + ", " + "完整类名：" + person.getClass().getName());
    }

    //验证所有的类都是Class类的实例对象
    @Test
    public void test2() throws ClassNotFoundException {
        //定义两个类型都未知的Class , 设置初值为null, 看看如何给它们赋值成Person类
        Class<?> class1 = null;
        Class<?> class2 = null;

        //写法1，可能会抛出ClassNotFoundException[多用这个写法]
        class1 = Class.forName("com.fahai.Person");
        System.out.println("(写法1) 包名：" + class1.getPackage().getName() +", " + "完整类名："+ class1.getName());

        //写法2
        class2 = Person.class;
        System.out.println("(写法2) 包名：" + class2.getPackage().getName() + ", " + "完整类名：" + class2.getName());
    }

    //通过Java反射机制，用Class创建类对象（反射存在的意义）
    @Test
    public void test3() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> class1 = null;
        class1 = Class.forName("com.fahai.Person");
        //由于这里不能带参数，所以你要实例化的这个类Person，一定要有无参构造函数哈～
        Person person = (Person) class1.newInstance();
        person.setAge(20);
        person.setName("张三");
        System.out.println(person.getName() + " : "+ person.getAge());

    }
    //通过Java反射机制得到一个类的构造函数，并实现创建带参实例对象
    @Test
    public void test4() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> class1 = null;
        Person person1 = null;
        Person person2 = null;
        class1 = Class.forName("com.fahai.Person");
        //得到一系列类构造函数集合
        Constructor<?>[] constructors = class1.getConstructors();
//        System.out.println(constructors[0].getParameterCount());
        person1 = (Person) constructors[0].newInstance();
        person1.setAge(30);
        person1.setName("张三");


        System.out.println(constructors[1].getParameterCount());
        person2 = (Person) constructors[1].newInstance(20, "张三");

        System.out.println( person1.getName() + " : " + person1.getAge()
                        + "  ,   " + person2.getName() + " : " + person2.getAge());
    }

    //通过Java反射机制操作成员变量，set 和 get
    @Test
    public void test5() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> class1 = null;
        class1 = Class.forName("com.fahai.Person");
        Object obj = class1.newInstance();

        //获取所有的成员变量
        Field[] fields = class1.getDeclaredFields();
        System.out.println(fields.length);
        System.out.println(fields[0]);
        System.out.println(fields[1].getName());

        fields[1].setAccessible(true);
        fields[1].set(obj, "胖虎先森");
        System.out.println("修改属性之后得到属性变量的值：" + fields[1].get(obj));
    }

    //通过Java反射机制得到类的一些属性：继承的接口，父类，函数信息，类型等
    @Test
    public void test6() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("com.fahai.SuperMan");

        //获取父类名称
        Class<?> superClass = class1.getSuperclass();
        System.out.println(superClass.getName());
        System.out.println("===============================================");

        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("类中的成员: " + fields[i]);
        }

        System.out.println("===============================================");
        Method[] methods = class1.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("Demo6,取得SuperMan类的方法：");
            System.out.println("函数名：" + methods[i].getName());
            System.out.println("函数返回类型：" + methods[i].getReturnType());
            System.out.println("函数访问修饰符：" + Modifier.toString(methods[i].getModifiers()));
            System.out.println("函数代码写法： " + methods[i]);
        }
        System.out.println("===============================================");
        //取得类实现的接口,因为接口类也属于Class,所以得到接口中的方法也是一样的方法得到哈
        Class<?> interfaces[] = class1.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println("实现的接口类名: " + interfaces[i].getName() );
        }
    }

    //通过Java反射机制调用类方法
    @Test
    public void test7() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> class1 = null;
        class1 = Class.forName("com.fahai.SuperMan");
        System.out.println("调用无参方法 fly()");
        Method method = class1.getMethod("fly");
        method.invoke(class1.newInstance());

        System.out.println("调用有参方法walk(int m)：");
        method = class1.getMethod("walk",int.class);
        method.invoke(class1.newInstance(),100);
    }
    //通过Java反射机制得到类加载器信息
    //在java中有三种类加载器:
    //1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
    //2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
    //3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
    @Test
    public void test8() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("com.fahai.SuperMan");

        String className = class1.getClassLoader().getClass().getName();
        System.out.println("类加载器类名:"+ className);
    }
}

class  Person{
    private int age;
    private String name;
    public Person(){

    }
    public Person(int age, String name){
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
class SuperMan extends Person implements ActionInterface
{
    private boolean BlueBriefs;

    public void fly()
    {
        System.out.println("超人会飞耶～～");
    }

    public boolean isBlueBriefs() {
        return BlueBriefs;
    }
    public void setBlueBriefs(boolean blueBriefs) {
        BlueBriefs = blueBriefs;
    }

    @Override
    public void walk(int m) {
        // TODO Auto-generated method stub
        System.out.println("超人会走耶～～走了" + m + "米就走不动了！");
    }
}
interface ActionInterface{
    public void walk(int m);
}