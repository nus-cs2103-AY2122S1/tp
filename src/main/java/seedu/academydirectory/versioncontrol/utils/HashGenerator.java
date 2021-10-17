package seedu.academydirectory.versioncontrol.utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    private final int numHex;
    private final MessageDigest digest;

    /**
     * Constructs a HashGenerator which can generate hash for a given file
     * @param method Hash algorithms supported: MD1, SHA1 and SHA256
     */
    public HashGenerator(HashMethod method) {
        int numHex1;
        MessageDigest digest1;
        try {
            MessageDigest digest = MessageDigest.getInstance(method.getValue());
            digest.reset();
            digest1 = digest;
            numHex1 = method.getNumHex();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            digest1 = null;
            numHex1 = 0;
        }
        numHex = numHex1;
        this.digest = digest1;
    }

    /**
     * Generates a hash for a given file
     * @param path Path to the given file
     * @return String which represents hash of the given file
     * @throws IOException Path not found
     */
    public String generateHashFromFile(Path path) throws IOException {
        InputStream is = Files.newInputStream(path);
        DigestInputStream dis = new DigestInputStream(is, digest);
        byte[] buffer = new byte[1024 * 8];
        while (true) {
            int byteRead = dis.read(buffer);
            if (byteRead == -1) {
                break;
            }
        };
        dis.close();
        return String.format("%0" + numHex + "x", new BigInteger(1, dis.getMessageDigest().digest()));
    }

    // TODO: Implement this as a test code
    public static void main(String[] argv) {
        HashGenerator generator = new HashGenerator(HashMethod.SHA256);
        Path path = Paths.get("data", "academydirectory.json");
        try {
            System.out.println(generator.generateHashFromFile(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
