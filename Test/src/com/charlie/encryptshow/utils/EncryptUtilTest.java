package com.charlie.encryptshow.utils;

/**
 * Created
 * Author:Charlie Wei[]
 * Email:charlie_net@163.com
 * Date:2015/10/16
 */

import android.util.Log;
import junit.framework.TestCase;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/**
 * 测试类
 */
public class EncryptUtilTest extends TestCase{

    public void testGenerateRSAKeyPair(){

        //  --------------



        // 密钥的生成
        //
        KeyPair keyPair = EncryptUtil.generateRSAKeyPair(1024);
        // 获取公钥，可以给任何人
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥，需要秘密保存
        PrivateKey privateKey = keyPair.getPrivate();

        Log.d("RSA","public = " + publicKey);
        Log.d("RSA","private = " + privateKey);

        // 将公钥转换为 RSAPublicKey
        RSAPublicKey publicKey1 = (RSAPublicKey)publicKey;
        BigInteger modulus = publicKey1.getModulus();// 求余
        Log.d("RSA","n = " + modulus);

        // 加密解密测试
        String str = "I love Android!";
        // 私钥加密
        byte[] encryptedData = EncryptUtil.rsaEncrypt(str.getBytes(), privateKey);
        // 公钥解密
        byte[] bytes = EncryptUtil.rsaDecrypt(encryptedData, publicKey);
        String s1 = new String(bytes);

        assertEquals(str,s1);

    }

    public void testToHex(){
        byte[] data = new byte[]{1,2};// 十六进制 0102
        String hex = EncryptUtil.toHex(data);

        // 检查hex的值是否是0102，参数一代表期望的数据，参数二 实际的数值
        assertEquals("0102", hex);

        // 解码
        byte[] d1 = EncryptUtil.fromHex(hex);
        // 检查是否数组比较返回true，如果是false，抛异常
        assertTrue(Arrays.equals(d1, data));

    }
}
