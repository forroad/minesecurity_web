package com.ycjw.minesecurity.utils;

import java.util.Random;

public class KeyUtil {

    private static volatile Random random=null;

    /**
     * 获取一个长度为len的字符串
     * @return
     */
    private static String getUniqueKey(int len){
        if (random==null){
            synchronized (KeyUtil.class){
                if (random==null){
                    random=new Random();
                }
            }
        }//if
        int num=random.nextInt(10^len)+10^len;
        String key=System.currentTimeMillis()+String.valueOf(num);
        return key;
    }

    /**
     * 获取长度为20的随机字符串
     * @return
     */
    public static synchronized String getUniqueKey_20(){
        return getUniqueKey(7);
    }

    /**
     * 获取长度为15的随机字符串
     * @return
     */
    public static synchronized String getUniqueKey_15(){
        return getUniqueKey(2);
    }

}
