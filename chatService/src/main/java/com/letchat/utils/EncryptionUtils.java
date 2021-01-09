package com.letchat.utils;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author alice
 */
public class EncryptionUtils {

    /**
     * 编码格式；默认使用uft-8
     */
    public static final String CHARSET = "utf-8";

    public static final String HMAC_SHA1 = "HmacSHA1";


    /**
     * 使用SHA1加密算法进行加密（不可逆）
     *
     * @param res 需要加密的原文
     * @param key 秘钥
     * @return 加密后字符串
     */
    public static String getShaEncryption(String res, String key) {
        return keyGeneratorMac(res, key);
    }

    private static String keyGeneratorMac(String res, String key) {
        try {
            SecretKey sk;
            if (key == null) {
                KeyGenerator kg = KeyGenerator.getInstance(EncryptionUtils.HMAC_SHA1);
                sk = kg.generateKey();
            } else {
                byte[] keyBytes = key.getBytes(CHARSET);
                sk = new SecretKeySpec(keyBytes, EncryptionUtils.HMAC_SHA1);
            }
            Mac mac = Mac.getInstance(EncryptionUtils.HMAC_SHA1);
            mac.init(sk);
            byte[] result = mac.doFinal(res.getBytes());
            return base64(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String base64(byte[] res) {
        return Base64.encode(res);
    }

}
