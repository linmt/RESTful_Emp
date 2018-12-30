package com.lmt.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by 张洲徽 on 2018/11/30.
 */
public class NoteUtil {
    public static String md5(String msg) throws NoSuchAlgorithmException {
        //利用md5对msg进行处理
        //有md5和sha两种，都是基于哈希算法，msg处理后长度是一致的
        MessageDigest md=MessageDigest.getInstance("MD5");
        byte[] input=msg.getBytes();
        byte[] output=md.digest(input);
        //将md5处理的output结果转成字符串
        //return new String(output);  这样是乱码
        //BASE64Encoder   旧版本可以用？
        String result= Base64.encodeBase64String(output);
        return result;
    }
    //测试用的，可以去掉
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(md5("1234"));
        System.out.println(md5("adfsdafasdfadf"));
    }
    public static String createId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
