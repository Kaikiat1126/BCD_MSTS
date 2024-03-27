package msts;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyAccess {
    public static PublicKey getPublicKey(String filename) throws Exception {
        String path = "KeyPairs/PublicKey/" + filename;
        byte[] keyBytes = Files.readAllBytes(Paths.get(path));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    public static PrivateKey getPrivateKey(String filename) throws Exception {
        String path = "KeyPairs/PrivateKey/" + filename;
        byte[] keyBytes = Files.readAllBytes(Paths.get(path));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    public static String getPublicKeyString(String filename) throws Exception {
        PublicKey pubKey = getPublicKey(filename);
        return java.util.Base64.getEncoder().encodeToString(pubKey.getEncoded());
    }

    public static String getPrivateKeyString(String filename) throws Exception {
        PrivateKey privKey = getPrivateKey(filename);
        return java.util.Base64.getEncoder().encodeToString(privKey.getEncoded());
    }
}
