package com.numb_little_bug.utils;

public class EncryptionWithKey {
    public static String encrypt(String str, String key) {
        char[] chars = str.toCharArray();
        char[] keys = key.toCharArray();
        int len = chars.length;
        int keyLen = keys.length;
        for (int i = 0; i < len; i++) {
            chars[i] = (char) (chars[i] ^ keys[i % keyLen]);
        }
        return new String(chars);
    }

    public static String decrypt(String str, String key) {
        return encrypt(str, key);
    }
}
