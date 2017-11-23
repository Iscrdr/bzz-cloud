package com.star.cloud.framework.security;



import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DeAndEncrpt {

    public static String enCode(String str){
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }
    public static String deCode(String str){
        return new String(Base64.getDecoder().decode(str),StandardCharsets.UTF_8);
    }
    public static void main(String[] args) {
        String str="admin";
        String encode = DeAndEncrpt.enCode(str);
        System.out.println(encode);
        String deCode = DeAndEncrpt.deCode(encode);
        System.out.println(deCode);

    }
}
