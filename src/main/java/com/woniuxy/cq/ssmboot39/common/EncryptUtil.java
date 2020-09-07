package com.woniuxy.cq.ssmboot39.common;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密工具
 */
public class EncryptUtil {

    public static  final  String ALGORITHM_NAME="SHA-256";
    public static  final  int HASH_ITERATIONS=1;

    /**
     * 加密方法
     * @param password
     * @return
     */
    public static String encrypt(String password){
        return encrypt(password,null);
    }

    /**
     * 加密方法
     * @param password
     * @return
     */
    public static String encrypt(String password,String salt){
        SimpleHash simpleHash = new SimpleHash(ALGORITHM_NAME,password,ByteSource.Util.bytes(salt),HASH_ITERATIONS);
        return simpleHash.toHex();
    }


}
