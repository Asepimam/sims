package com.sims.sims.shared.security;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.*;
import java.util.Base64;

public class RsaKeyUtils {

    public static PrivateKey readPrivateKey(InputStream inputStream) throws Exception {
        String key = new String(inputStream.readAllBytes());
        key = key.replaceAll("-----BEGIN PRIVATE KEY-----", "")
                 .replaceAll("-----END PRIVATE KEY-----", "")
                 .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    public static PublicKey readPublicKey(InputStream inputStream) throws Exception {
        String key = new String(inputStream.readAllBytes());
        key = key.replaceAll("-----BEGIN PUBLIC KEY-----", "")
                 .replaceAll("-----END PUBLIC KEY-----", "")
                 .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }
}
