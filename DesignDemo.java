
import org.junit.Test;

import java.util.Date;


/**
 * Created by zhanghao on 16/9/5.
 */
public class DesignDemo {
    @org.junit.Test
    public void test1(){
        Car BMW = CarFactory.createCar("BMW");
        Car BZ = CarFactory.createCar("BZ");
        Car AD = CarFactory.createCar("AD");
    }
    @Test
    public void test2(){
        LogFactory logFactory = new FileLogFactory();
        Log fileLog = logFactory.careteLog();
        LogFactory logFactory1 = new DatabaseFactory();
        Log databaseLog = logFactory1.careteLog();
        fileLog.writeLog();
        databaseLog.writeLog();
    }

//    @Test
//    public void client(){
//        Builder builder = new ConcreteBuilder();
//        Director director = new Director(builder);
//        director.construct();
//        Product product = builder.retrieveResult();
//        System.out.println(product.getPart1());
//        System.out.println(product.getPart2());
//    }
    @Test
    public void client(){
        Builder builder = new WelcomeBuilder();
        Director director = new Director(builder);
        director.construct("toAdress@163.com", "fromAdress@163.com");
    }
}

//懒汉模式
//类加载时 实例化 如果多次加载 会多次实例化
class Singleton{
    private static Singleton instance = new Singleton();
    private Singleton(){

    }
    public static Singleton getInstance(){
        return instance;
    }
}

//饿汉模式
//使用了同步 速度会慢一点
class Singleton2{
    private static Singleton2 instance = null;
    private Singleton2(){

    }
    public static synchronized Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}

//工厂模式
//简单工厂
abstract class Car{
    public Car(){}
}

class BMWCar extends Car{
    public BMWCar(){
        System.out.println("崭新的宝马被制造出来了。。。");
    }
}

class BZCar extends Car {
    public BZCar(){
        System.out.println("锃亮的奔驰被制造出来了。。。");
    }
}
class ADCar extends Car{
    public ADCar(){
        System.out.println("奥迪出世了。。。");
    }
}

class CarFactory{
    public static Car createCar(String s){
        switch (s){
            case "BMW":
                return new BMWCar();

            case "BZ":
                return new BZCar();

            case "AD":
                return new ADCar();

            default:
                break;
        }
        return null;
    }
}
//工厂模式:日志记录器

abstract class Log{
    public Log(){}
    public abstract void writeLog();
}

class FileLog extends Log{
    public FileLog(){}

    @Override
    public void writeLog() {
        System.out.println("记录fileLog");
    }
}

class DatabaseLog extends Log{
    public DatabaseLog(){}

    @Override
    public void writeLog() {
        System.out.println("记录DatabaseLog");
    }

}

abstract class LogFactory{
    public LogFactory(){}
    public abstract Log careteLog();
}
class FileLogFactory extends LogFactory{
    public FileLogFactory(){}
    @Override
    public Log careteLog() {
        return new FileLog();
    }
}
class DatabaseFactory extends LogFactory{
    public DatabaseFactory(){}
    @Override
    public Log careteLog() {
        return new DatabaseLog();
    }
}

//建造者模式
//class Product{
//    /**
//     * 定义一些关于产品的操作
//     */
//    private String part1;
//    private String part2;
//
//    public String getPart1() {
//        return part1;
//    }
//
//    public void setPart1(String part1) {
//        this.part1 = part1;
//    }
//
//    public String getPart2() {
//        return part2;
//    }
//
//    public void setPart2(String part2) {
//        this.part2 = part2;
//    }
//}
//abstract class Builder{
//    public abstract void buildPart1();
//    public abstract void buildPart2();
//    public abstract Product retrieveResult();
//}
//
//class ConcreteBuilder extends Builder{
//
//    private Product product = new Product();
//    @Override
//    public void buildPart1() {
//        product.setPart1("编号：9527");
//    }
//
//    @Override
//    public void buildPart2() {
//        product.setPart2("名称：XXX");
//    }
//
//    @Override
//    public Product retrieveResult() {
//        return product;
//    }
//}
////导演者
//class Director{
//    /**
//     * 持有当前需要使用的建造器对象
//     */
//    private Builder builder;
//
//    /**
//     * 构造方法，传入建造器对象
//     * @param builder
//     */
//    public Director(Builder builder){
//        this.builder = builder;
//    }
//    /**
//     * 产品构造方法，负责调用各个零件建造方法
//     */
//    public void construct(){
//        builder.buildPart1();
//        builder.buildPart2();
//    }
//}
//
abstract class AutoMessage{
    private String to;
    private String from;
    private String subject;
    private String body;
    private Date sendDate;
    public void send(){
        System.out.println("收件人地址：" + to);
        System.out.println("发件人地址：" + from);
        System.out.println("标题：" + subject);
        System.out.println("内容：" + body);
        System.out.println("发送日期：" + sendDate);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}

class WelcomeMessage extends AutoMessage{
    /**
     * 构造器
     */
    public WelcomeMessage(){
        System.out.println("发送欢迎信息");
    }
}

class GoodbyeMessage extends AutoMessage{
    /**
     * 构造器
     */
    public GoodbyeMessage(){
        System.out.println("发送欢送信息");
    }
}

abstract class Builder{
    protected AutoMessage msg;
    public abstract void buildSubject();
    public abstract void buildBody();

    public void buildTo(String to){
        msg.setTo(to);
    }
    public void buildFrom(String from){
        msg.setFrom(from);
    }
    public void buildSendDate(){
        msg.setSendDate(new Date());
    }
    /**
     * 邮件产品完成后，用此方法发送邮件
     * 此方法相当于产品返回方法
     */
    public void sendMessage(){
        msg.send();
    }
}

class WelcomeBuilder extends Builder{
    public WelcomeBuilder(){
        msg = new WelcomeMessage();
    }

    @Override
    public void buildSubject() {
        msg.setSubject("欢迎标题");
    }

    @Override
    public void buildBody() {
        msg.setBody("欢迎内容");
    }
}

class GoodbyeBuilder extends Builder{
    public GoodbyeBuilder(){
        msg = new GoodbyeMessage();
    }

    @Override
    public void buildSubject() {
        msg.setSubject("欢送标题");
    }

    @Override
    public void buildBody() {
        msg.setBody("欢送内容");
    }
}

class Director{
    Builder builder;
    public Director(Builder builder){
        this.builder = builder;
    }

    /**
     * 产品构造方法，负责调用各零件的建造方法
     */
    public void construct(String toAddress, String fromAddress){
        this.builder.buildTo(toAddress);
        this.builder.buildFrom(fromAddress);
        this.builder.buildSubject();
        this.builder.buildBody();
        this.builder.buildSendDate();
        this.builder.sendMessage();

    }
}

