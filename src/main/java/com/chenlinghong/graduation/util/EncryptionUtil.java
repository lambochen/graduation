package com.chenlinghong.graduation.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.util.StringUtils;

/**
 * @Description 加密解密
 * @Author chenlinghong
 * @Date 2018/12/1 23:13
 **/
public final class EncryptionUtil {

    private static String salt = "fh15,.>?>..'dsfg345ig435fggdfdgfd6546;';':'.'.345fds30R%&*Y&GUGi;';]::";

    /**
     * CC SHA-1加密算法
     *
     * @param plaintext 明文
     * @return 密文
     */
    public static String ccSHA1(String plaintext) {
        String base = plaintext + "/" + salt;
        return DigestUtils.sha1Hex(base);
    }

    /**
     * CC MD5加密
     *
     * @param plaintext 明文
     * @return 密文
     */
    public static String ccMD5(String plaintext) {
        String base = plaintext + "/" + salt;
        return DigestUtils.md5Hex(base.getBytes());
    }


    /**
     * 使用BouncyCastle实现的Base64进行加密
     *
     * @param plaintext 明文
     * @return 密文
     */
    public static String encodeByBase64(String plaintext) {
        if (StringUtils.isEmpty(plaintext)) {
            return null;
        }
        byte[] encodeBytes = Base64.encode(plaintext.getBytes());
        return new String(encodeBytes);
    }

    /**
     * 使用BouncyCastle实现的Base64进行解密
     *
     * @param ciphertext 解密
     * @return
     */
    public static String decodeByBase64(String ciphertext) {
        byte[] decodeBytes = Base64.decode(ciphertext);
        return new String(decodeBytes);
    }

    /**
     * 与客户端约定好的对称加密算法【算法、密钥】
     *
     * @param plain 明文
     * @return 密文
     */
    public static String encodeClient(String plain) {
        //TODO
        return plain;
    }

    /**
     * 与客户端约定好的对称加密算法【算法、密钥】
     *
     * @param cipher 密文
     * @return 明文
     */
    public static String decodeClient(String cipher) {
        //TODO
        return cipher;
    }


}
