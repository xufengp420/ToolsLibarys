package com.example.xufeng.toolslibary.utils;

import com.qiniu.android.utils.UrlSafeBase64;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by abillchen on 2017/4/27.
 */

public class QiniuUtils {

    public static final String IMG_IP = "http://images.abillchen.top/";

    /**
     * 生成七牛上传token
     */
    public static String getQiniuToken() {
        //七牛后台的key
        String AccessKey = "WM-t44rkgisvXWZMSMXRR8i_C3-fFKkcIwg7cZ51";
        //七牛后台的secret
        String SecretKey = "2680o269j1_oSN7RH29029Omjj-2LD775FbRogpB";

        String uploadToken = "";
        try {

            JSONObject json = new JSONObject();
            long dataline = System.currentTimeMillis() / 1000 + 3600;
            json.put("deadline", dataline);// 有效时间为一个小时
            json.put("scope", "chenxu");//七牛存储空间名称，可不是外链域名喔
            String encodedPutPolicy = UrlSafeBase64.encodeToString(json.toString().getBytes());
            byte[] sign = HmacSHA1Encrypt(encodedPutPolicy, SecretKey);
            String encodedSign = UrlSafeBase64.encodeToString(sign);
            uploadToken = AccessKey + ':' + encodedSign + ':' + encodedPutPolicy;

        } catch (Exception e) {

        }
        return uploadToken.toString();
    }

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
            throws Exception {
        byte[] data = encryptKey.getBytes("UTF-8");
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance("HmacSHA1");
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes("UTF-8");
        // 完成 Mac 操作
        return mac.doFinal(text);
    }

    /**
     * 生成上传文件名
     *
     * @param path 文件路径
     */
    public static String getUploadFileName(String path) {

        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        String md5 = encryptionMD5(FileTobyte(path));
        return str + "_" + md5.substring(24) + ".png";
    }

    /**
     * File转byte[]
     *
     * @param filePath
     */
    public static byte[] FileTobyte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * MD5加密
     *
     * @param byteStr 需要加密的内容
     * @return 返回 byteStr的md5值
     */
    public static String encryptionMD5(byte[] byteStr) {
        MessageDigest messageDigest = null;
        StringBuffer md5StrBuff = new StringBuffer();
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(byteStr);
            byte[] byteArray = messageDigest.digest();
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5StrBuff.toString();
    }
}
