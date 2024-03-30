package msts;

import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class DigitalSignature {

    private static DigitalSignature instance = null;
    private static final String ALG = "SHA256withRSA";
    private Signature sign;

    public DigitalSignature() {
        try {
            sign = Signature.getInstance(ALG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DigitalSignature getInstance() {
        if (instance == null) {
            instance = new DigitalSignature();
        }
        return instance;
    }

    public byte[] getSignature(String text, PrivateKey key) {
        try {
            sign.initSign(key);
            sign.update(text.getBytes());
            return sign.sign();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isTextAndSignatureValid(String text, byte[] signature, PublicKey key) {
        try {
            sign.initVerify(key);
            sign.update(text.getBytes());
            return sign.verify(signature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isTextAndSignatureValid(String text, String signature, PublicKey key) {
        try {
            byte[] signBytes = Base64.getDecoder().decode(signature);
            return isTextAndSignatureValid(text, signBytes, key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
