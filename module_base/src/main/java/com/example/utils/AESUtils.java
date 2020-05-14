package com.example.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private String sKey = "@l@x@y%i$l&y@52g";
    private String ivParameter = "1234567890123456";
    private static AESUtils instance = null;

    private AESUtils() {

    }

    public static AESUtils getInstance() {
        if (instance == null)
            instance = new AESUtils();
        return instance;
    }

    // 加密
    public static String encrypt(String encData, String secretKey, String vector) {
        try {
            if (secretKey == null) {
                return null;
            }
            if (secretKey.length() != 16) {
                return null;
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = secretKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
            return Base64.encodeToString(encrypted, Base64.DEFAULT);// 此处使用BASE64做转码。（处于android.util包）
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // 加密
    public String encrypt(String sSrc) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return Base64.encodeToString(encrypted, Base64.DEFAULT);// 此处使用BASE64做转码。
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 解密
    public String decrypt(String sSrc) {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(sSrc, Base64.DEFAULT);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    // 解密
    public String decrypt(String sSrc, String key) {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(sSrc, Base64.DEFAULT);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }

    }
}