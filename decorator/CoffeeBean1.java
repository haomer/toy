package com.zhanghao.decorator;

/**
 * Created by zhanghao on 16/11/22.
 */
public class CoffeeBean1 implements Beverage {
    private String description = "第一种咖啡豆";
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return 50;
    }
}
