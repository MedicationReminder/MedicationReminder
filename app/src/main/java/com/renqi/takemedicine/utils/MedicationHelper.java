package com.renqi.takemedicine.utils;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Xu Wei on 2017/7/4.
 * 共有方法工具类
 */

public class MedicationHelper {
    private static Logger log = LoggerFactory.getLogger(MedicationHelper.class);
    public static MedicationHelper mh = new MedicationHelper();
    private static final Gson gson = new Gson(); // gson是线程安全的，可以使用单例避免重复创建

}