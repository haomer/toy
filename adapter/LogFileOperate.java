package com.zhanghao.adapter;

import java.util.List;

/**
 * Created by zhanghao on 16/11/17.
 */
public class LogFileOperate implements LogFileOperateApi {
    //设置日志文件的路径和文件名称
    private String logFileName="file.log";

    public LogFileOperate(String logFileName) {
        if(logFileName!=null){
            this.logFileName = logFileName;
        }
    }

    @Override
    public List<LogBean> readLogFile() {
        System.out.println("从"+this.logFileName + "文件中读取log日志。");
        return null;
    }

    @Override
    public void writeLogFile(List<LogBean> list) {
        System.out.println("往" +this.logFileName + "文件中写log日志。");
    }
}
