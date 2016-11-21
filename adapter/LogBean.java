package com.zhanghao.adapter;

/**
 * Created by zhanghao on 16/11/17.
 */
public class LogBean {
    private String logId; //编号
    private String opeUserId; //操作人员

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getOpeUserId() {
        return opeUserId;
    }

    public void setOpeUserId(String opeUserId) {
        this.opeUserId = opeUserId;
    }

    @Override
    public String toString() {
        return "LogBean{" +
                "logId='" + logId + '\'' +
                ", opeUserId='" + opeUserId + '\'' +
                '}';
    }
}
