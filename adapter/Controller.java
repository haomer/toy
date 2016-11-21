package com.zhanghao.adapter;

//import org.junit.Test;

/**
 * Created by zhanghao on 16/11/17.
 */
public class Controller {

//    //只用写入日志文件
//    @Test
//    public void test1(){
//        //写入
//        LogFileOperateApi logFileOperateApi = new LogFileOperate("shajk.log");
//        logFileOperateApi.writeLogFile(null);
//        //读取
//        logFileOperateApi.readLogFile();
//    }
//
//    //现在需要写入文件的同时 也需要写入数据库
//    @Test
//    public void test2(){
//        //写入
////        LogFileOperateApi logFileOperateApi = new LogFileOperate("shajk.log");
//        LogAdapter logAdapter= new LogAdapter("shjakhsjka");
//        logAdapter.createLog(null);
//    }
    public static void main(String[] args){
        //写入
//        LogFileOperateApi logFileOperateApi = new LogFileOperate("shajk.log");
        LogAdapter logAdapter= new LogAdapter("shjakhsjka");
        logAdapter.createLog(null);
    }
}
