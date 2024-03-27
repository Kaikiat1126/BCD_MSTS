package msts;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;

public class Hasher {

    public static String sha256( byte[] blockBytes ) {
        try {
            byte[] hashBytes = MessageDigest.getInstance("SHA-256").digest( blockBytes );
            return Base64.getEncoder().encodeToString( hashBytes );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String hash(String input, String algorithm) {
        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance(algorithm);
            md.update( input.getBytes() );

            byte[] hashBytes = md.digest();
            return Base64.getEncoder().encodeToString( hashBytes );
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String saltHash(String input, byte[] salt, String algorithm) {
        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance(algorithm);
            md.update( input.getBytes() );
            md.update( salt );

            byte[] hashBytes = md.digest();
            return Base64.getEncoder().encodeToString( hashBytes );
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String md5(String input) {
        return hash(input, "MD5");
    }

    public static String rsa(String input) {
        return hash(input, "RSA");
    }

    public static String rsa(String input, byte[] salt) {
        return saltHash(input, salt, "RSA");
    }

    public static String sha256(String input) {
        return hash(input, "SHA-256");
    }

    public static String sha256(String input, byte[] salt) {
        return saltHash(input, salt, "SHA-256");
    }

    public static String generateUUID(String input) { return UUID.nameUUIDFromBytes(input.getBytes()).toString(); }
}
