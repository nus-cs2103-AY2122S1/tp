package seedu.address.commons.util;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

    public static Cipher createCipherInstance(int opmode) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] keyBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            String algorithm = "AES";
            SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
            cipher.init(opmode, key, new IvParameterSpec(new byte[16]));
            return cipher;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            System.out.println(e);
            return null;
        }
    }

    public static SealedObject encryptSerializableObject(Serializable serializableObject) throws IOException {
        try {
            return new SealedObject(serializableObject, createCipherInstance(Cipher.ENCRYPT_MODE));
        } catch (IllegalBlockSizeException e) {
            System.out.println(e);
        }
        return null;
    }

    public static Serializable decryptSealedObject(SealedObject sealedObject) {
        try {
            return (Serializable) sealedObject.getObject(createCipherInstance(Cipher.DECRYPT_MODE));
        } catch (IOException | ClassNotFoundException | IllegalBlockSizeException
                | BadPaddingException e) {
            System.out.println(e);
        }
        return null;
    }
}
