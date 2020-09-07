package com.woniuxy.cq.ssmboot39.common;

import java.util.Random;

public class RandomUtil {

    private static final String ALPHABET="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS="1234567890";
    private static final String ALPHABETNUMBERS=ALPHABET+NUMBERS;

    /**
     * 随机生成任意长度的code，可用于生成验证码
     * @param x 位数
     * @return
     */
    public static String randomNumber(int x){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < x; i++) {
            sb.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }
        return sb.toString();
    }
    /**
     * 随机生成任意长度的code，可用于生成验证码
     * @param x 位数
     * @return
     */
    public static String randomCode(int x){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < x; i++) {
            sb.append(ALPHABETNUMBERS.charAt(random.nextInt(ALPHABETNUMBERS.length())));
        }
        return sb.toString();
    }


}
