package com.smartagilify.core.util;

import com.smartagilify.core.constant.ErrorConstant;
import com.smartagilify.core.exceptions.InternalServerException;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class HashUtil {

    private static final ThreadLocal<MessageDigest> messageDigestThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            return MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            throw new InternalServerException(ErrorConstant.INTERNAL_SERVER_EXCEPTION);
        }
    });

    private HashUtil() {
    }

    public static String createIdHashcode(Object objectId, String key, Object userId) {
        MessageDigest digest = messageDigestThreadLocal.get();
        digest.update(key.getBytes(StandardCharsets.UTF_8));
        digest.update(userId.toString().getBytes(StandardCharsets.UTF_8));
        byte[] encodedHash = digest.digest(objectId.toString().getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void evacuateDigestThreadLocal(){
        messageDigestThreadLocal.remove();
    }

}
