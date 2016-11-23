package com.zhanghao.decorator;

/**
 * Created by zhanghao on 16/11/22.
 */
public class Milk extends Decorator {
    private String description = "加了牛奶！";
    private Beverage beverage = null;
    public Milk(Beverage beverage){
        this.beverage  = beverage;
    }

    public String getDescription(){
        return beverage.getDescription()+"\n"+description;
    }

    public double getPrice(){
        return beverage.getPrice()+20; //20表示牛奶的价格
    }
}
