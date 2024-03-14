package msts;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyPairMaker {

    private final String algorithm = "RSA";
    private KeyPairGenerator keygen;
    private KeyPair keyPair;
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    private static PublicKey getPublicKey(){
        return publicKey;
    }

    private static PrivateKey getPrivateKey(){
        return privateKey;
    }

    private KeyPairMaker() {
        try {
            keygen = KeyPairGenerator.getInstance(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void create() {
        KeyPairMaker keyMaker = new KeyPairMaker();
        keyMaker.keyPair = keyMaker.keygen.generateKeyPair();
        publicKey = keyMaker.keyPair.getPublic();
        privateKey = keyMaker.keyPair.getPrivate();
    }

    private static void storeKeyPair(byte[] keyBytes, String path) {
        File f = new File(path);
        f.getParentFile().mkdirs();
        try {
            Files.write(Paths.get(path), keyBytes, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateKeyPair(String uuid) {
        KeyPairMaker.create();
        byte[] publicKey = KeyPairMaker.getPublicKey().getEncoded();
        byte[] privateKey = KeyPairMaker.getPrivateKey().getEncoded();

        KeyPairMaker.storeKeyPair(publicKey, "KeyPairs/PublicKey/" + uuid);
        KeyPairMaker.storeKeyPair(privateKey, "KeyPairs/PrivateKey/" + uuid);
    }

}
