package com.zhanghao.adapter;

/**
 * Created by zhanghao on 16/11/17.
 */
public class LogAdapter extends LogFileOperate implements LogDbOpeApi {


    public LogAdapter(String logFileName) {
        super(logFileName);
    }

    @Override
    public void createLog(LogBean logBean) {
        System.out.println("log日志写入数据库");
        super.writeLogFile(null);
    }
}
