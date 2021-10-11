package seedu.address.commons.util;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;

public class EncryptionUtil {

    private static final Logger logger = LogsCenter.getLogger(EncryptionUtil.class);

    public static Cipher createCipherInstance(int opmode) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] keyBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            String algorithm = "AES";
            SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
            cipher.init(opmode, key, new IvParameterSpec(new byte[16]));
            return cipher;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            logger.warning("Error create new Cipher instance with opmode" + opmode + ": " + e);
            throw new IllegalStateException(e);
        }
    }

    public static SealedObject encryptSerializableObject(Serializable serializableObject) throws IOException {
        try {
            return new SealedObject(serializableObject, createCipherInstance(Cipher.ENCRYPT_MODE));
        } catch (IllegalBlockSizeException e) {
            logger.warning("Error encrypted serializable object " + serializableObject + ": " + e);
            throw new IOException(e);
        }
    }

    public static Serializable decryptSealedObject(SealedObject sealedObject) throws IOException {
        try {
            return (Serializable) sealedObject.getObject(createCipherInstance(Cipher.DECRYPT_MODE));
        } catch (IOException | ClassNotFoundException | IllegalBlockSizeException
                | BadPaddingException e) {
            logger.warning("Error decrypting sealed object " + sealedObject + ": " + e);
            throw new IOException(e);
        }
    }
}
