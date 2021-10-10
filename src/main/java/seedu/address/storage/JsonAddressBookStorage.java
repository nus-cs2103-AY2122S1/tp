package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.EncryptedJsonUtil;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = EncryptedJsonUtil.readEncryptedJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            System.out.println(ive.getMessage());
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }

//        try {
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            byte[] keyBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
//            String algorithm = "AES";
//            SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
//            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
//            CipherInputStream cipherInputStream =
//                    new CipherInputStream(new BufferedInputStream(new FileInputStream("data/file")), cipher);
//            ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
//            SealedObject sealedObject = (SealedObject) inputStream.readObject();
//            String jsonString = (String) sealedObject.getObject(cipher);
//            jsonAddressBook =
//                    Optional.of(JsonUtil.fromJsonString(jsonString, JsonSerializableAddressBook.class));
//
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
//                | IOException | ClassNotFoundException | IllegalBlockSizeException
//                | BadPaddingException | InvalidAlgorithmParameterException e) {
//            System.out.println(e);
//        }

//        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
//                filePath, JsonSerializableAddressBook.class);

    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        EncryptedJsonUtil.saveEncryptedJsonFile(new JsonSerializableAddressBook(addressBook), filePath);

//        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);

        //        try {
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            byte[] keyBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
//            String algorithm = "AES";
//            SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
//            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
//            SealedObject sealedObject =
//                    new SealedObject(JsonUtil.toJsonString(new JsonSerializableAddressBook(addressBook)), cipher);
//            CipherOutputStream cipherOutputStream =
//                    new CipherOutputStream(new BufferedOutputStream(new FileOutputStream("data/file")), cipher);
//            ObjectOutputStream outputStream = new ObjectOutputStream(cipherOutputStream);
//            outputStream.writeObject(sealedObject);
//            outputStream.close();
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
//                | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
//            System.out.println(e);
//        }

//
    }

}
