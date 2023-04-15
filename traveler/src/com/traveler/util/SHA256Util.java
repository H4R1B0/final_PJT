package com.traveler.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHA256Util {

    /**
     * SHA-256 암호화
     *
     * @param source       원본
     * @param salt(String) SALT 값
     */
    public static String getEncrypt(String source, String salt) {
        return getEncrypt(source, salt.getBytes());
    }

    /**
     * SHA-256 암호화
     *
     * @param source       원본
     * @param salt(byte[]) SALT 값
     */
    public static String getEncrypt(String source, byte[] salt) {

        String result = "";

        byte[] sourceBytes = source.getBytes();
        byte[] bytes = new byte[sourceBytes.length + salt.length];

        System.arraycopy(sourceBytes, 0, bytes, 0, sourceBytes.length);
        System.arraycopy(salt, 0, bytes, sourceBytes.length, salt.length);

        try {
            // SHA-256 암호화 방식 지정
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);

            byte[] byteData = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * SALT 값을 얻어온다.
     */
    public static String generateSalt() {
        Random random = new Random();

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < salt.length; i++) {
            // byte 값을 Hex 값으로 바꾸기.
            sb.append(String.format("%02x", salt[i]));
        }

        return sb.toString();
    }
}