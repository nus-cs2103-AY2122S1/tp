package seedu.address.storage;

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

    public static SealedObject encryptSerializableObject(Serializable serializableObject) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] keyBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            String algorithm = "AES";
            SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
            return new SealedObject(serializableObject, cipher);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            System.out.println(e);
        }
        return null;
    }

    public static Object decryptSealedObject(SealedObject sealedObject) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] keyBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            String algorithm = "AES";
            SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
            return sealedObject.getObject(cipher);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IOException | ClassNotFoundException | IllegalBlockSizeException
                | BadPaddingException | InvalidAlgorithmParameterException e) {
            System.out.println(e);
        }
        return null;
    }
}
