package com.zhanghao.adapter;

import java.util.List;

/**
 * Created by zhanghao on 16/11/17.
 */
public interface LogFileOperateApi {
    public List<LogBean> readLogFile();

    public void writeLogFile(List<LogBean> list);
}
