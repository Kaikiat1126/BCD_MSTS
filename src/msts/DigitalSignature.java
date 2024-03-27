package msts;

import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;

public class DigitalSignature {

    private static final String ALG = "SHA256withRSA";
    private Signature sign;

    public DigitalSignature() {
        try {
            sign = Signature.getInstance(ALG);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
