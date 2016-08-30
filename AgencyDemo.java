package com.fahai;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhanghao on 16/8/30.
 */
public class AgencyDemo {
    /**
     * 静态代理
     * 我们需要在运行前手动创建代理类，这意味着如果有很多代理的话会很累；
     * 其次代理类 Agent 和 被代理类 Star 必须实现同样的接口，万一接口有变动，代理、被代理类都得修改，容易出问题。
     */
    @Test
    public void test(){
        Star huangBo = new Star("HuangBo");
        Agent agent = new Agent(huangBo);
        agent.movieShow(50000000);
        agent.tvShow(5);
    }

    /**
     * 动态代理
     *
     */
    @Test
    public void test2(){
        Star huangBo = new Star("HuangBo");
        ProxyHandler proxyHandler = new ProxyHandler(huangBo);
        IMovieStar agent = (IMovieStar) proxyHandler.getProxy();
        agent.movieShow(1000000000);
        agent.movieShow(100);
        ISingerStar agent1 = (ISingerStar)proxyHandler.getProxy();
        agent1.sing(1024);
    }
}

//影星接口
interface IMovieStar{
    /**
     * 演电影
     * @param money 演电影的片酬
     */
    void movieShow(int money);

    /**
     * 演电视剧
     * @param money 演电视剧的片酬
     */
    void tvShow(int money);
}

//歌星接口
interface ISingerStar{
    /**
     * 唱歌
     * @param number 歌曲数
     */
    void sing(int number);
}

class Star implements IMovieStar, ISingerStar{
    private String name;

    public Star(String name) {
        this.name = name;
    }

    @Override
    public void movieShow(int money) {
        System.out.println(name + "出演了部片酬"+money + "元的电影");
    }

    @Override
    public void tvShow(int money) {
        System.out.println(name + "出演了部片酬"+money + "元的电视剧");
    }

    @Override
    public void sing(int number) {
        System.out.println(name + "唱了"+ number + "首歌。");
    }
}

class Agent implements IMovieStar{
    /**
     * 代理的明星
     */
    Star mTarget;

    public Agent(Star mTarget) {
        this.mTarget = mTarget;
    }

    @Override
    public void movieShow(int money) {
        if(money < 30000000){
            System.out.println(money+"块钱？！你雇黄子韬去演电影吧~");
            return;
        }
        mTarget.movieShow(money);
    }

    @Override
    public void tvShow(int money) {
        if(money < 30000000){
            System.out.println(money+"块钱？！你雇黄子韬去演电视剧吧~");
            return;
        }
        mTarget.tvShow(money);
    }
}

/**
 * InvocationHandler invocationHandler //最关键的接口！它只有一个 invoke 方法，是代理类进行 拦截操作 的入口，一般需要自定义一个 Handler 来实现方法增强
 * 在 ProxyHandler 中我们创建了 getProxy() 方法，这个方法用于调用 Proxy.newProxyInstance(…) 方法生成代理类
 */
class ProxyHandler implements InvocationHandler{
    //被代理的对象
    private Object mTarget;
    public ProxyHandler(Object target){
        this.mTarget = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if(methodName.equals("movieShow") || methodName.equals("tvShow")){
            if(args[0] instanceof Integer && ((int)args[0]) < 30000000){
                System.out.println(((int) args[0]) + "块钱？！你雇 HuangZiTao 演去吧！");
                return null;
            }
        }
        Object result = method.invoke(mTarget, args);

        return result;
    }
    public Object getProxy() {
        return Proxy.newProxyInstance(mTarget.getClass().getClassLoader(), mTarget.getClass().getInterfaces(), this);
    }


}