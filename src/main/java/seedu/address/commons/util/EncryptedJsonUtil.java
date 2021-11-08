package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;

/**
 * Converts a Java object instance to an encrypted Json file and vice versa
 */
public class EncryptedJsonUtil {

    private static final Logger logger = LogsCenter.getLogger(EncryptedJsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .registerModule(new SimpleModule("SimpleModule")
                    .addSerializer(Level.class, new ToStringSerializer())
                    .addDeserializer(Level.class, new EncryptedJsonUtil.LevelDeserializer(Level.class)));

    static <T> void serializeObjectToEncryptedJsonFile(Path encryptedJsonFile, T objectToSerialize) throws IOException {
        String jsonString = toJsonString(objectToSerialize);
        FileUtil.writeToEncryptedFile(encryptedJsonFile, EncryptionUtil.encryptSerializableObject(jsonString));
    }

    static <T> T deserializeObjectFromEncryptedJsonFile(Path encryptedJsonFile, Class<T> classOfObjectToDeserialize)
            throws IOException {
        String jsonString = (String) EncryptionUtil.decryptSealedObject(
                FileUtil.readFromEncryptedFile(encryptedJsonFile));
        return fromJsonString(jsonString, classOfObjectToDeserialize);
    }

    /**
     * Returns the Json object from the given file or {@code Optional.empty()} object if the file is not found.
     * If any values are missing from the file, default values will be used, as long as the file is a valid json file.
     * @param filePath cannot be null.
     * @param classOfObjectToDeserialize Encrypted Json file has to correspond to the structure in the class given here.
     * @throws DataConversionException if the file format is not as expected.
     */
    public static <T> Optional<T> readEncryptedJsonFile(
            Path filePath, Class<T> classOfObjectToDeserialize) throws DataConversionException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("Json file " + filePath + " not found");
            return Optional.empty();
        }

        T jsonFile;

        try {
            jsonFile = deserializeObjectFromEncryptedJsonFile(filePath, classOfObjectToDeserialize);
        } catch (IOException e) {
            logger.warning("Error reading from encryptedJsonFile file " + filePath + ": " + e);
            throw new DataConversionException(e);
        }

        return Optional.of(jsonFile);
    }

    /**
     * Saves the Json object to the specified encrypted file.
     * Overwrites existing file if it exists, creates a new file if it doesn't.
     * @param jsonFile cannot be null
     * @param filePath cannot be null
     * @throws IOException if there was an error during writing to the file
     */
    public static <T> void saveEncryptedJsonFile(T jsonFile, Path filePath) throws IOException {
        requireNonNull(filePath);
        requireNonNull(jsonFile);

        serializeObjectToEncryptedJsonFile(filePath, jsonFile);
    }

    /**
     * Converts a given string representation of a JSON data to instance of a class
     * @param <T> The generic type to create an instance of
     * @return The instance of T with the specified values in the JSON string
     */
    public static <T> T fromJsonString(String json, Class<T> instanceClass) throws IOException {
        return objectMapper.readValue(json, instanceClass);
    }

    /**
     * Converts a given instance of a class into its JSON data string representation
     * @param instance The T object to be converted into the JSON string
     * @param <T> The generic type to create an instance of
     * @return JSON data representation of the given class instance, in string
     */
    public static <T> String toJsonString(T instance) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(instance);
    }

    /**
     * Contains methods that retrieve logging level from serialized string.
     */
    private static class LevelDeserializer extends FromStringDeserializer<Level> {

        protected LevelDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        protected Level _deserialize(String value, DeserializationContext ctxt) {
            return getLoggingLevel(value);
        }

        /**
         * Gets the logging level that matches loggingLevelString
         * <p>
         * Returns null if there are no matches
         *
         */
        private Level getLoggingLevel(String loggingLevelString) {
            return Level.parse(loggingLevelString);
        }

        @Override
        public Class<Level> handledType() {
            return Level.class;
        }
    }

}
