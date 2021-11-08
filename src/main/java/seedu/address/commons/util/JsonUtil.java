package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.ext.NioPathDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;


/**
 * Converts a Java object instance to JSON and vice versa
 */
public class JsonUtil {

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .registerModule(new SimpleModule("SimpleModule")
                    .addSerializer(Level.class, new ToStringSerializer())
                    .addSerializer(Path.class, new ToStringSerializer())
                    .addSerializer(Image.class, new ImageSerializer())
                    .addDeserializer(Level.class, new LevelDeserializer(Level.class))
                    .addDeserializer(Path.class, new NioPathDeserializer())
                    .addDeserializer(Image.class, new ImageDeserializer(Image.class)));

    static <T> void serializeObjectToJsonFile(Path filePath, T objectToSerialize) throws IOException {
        FileUtil.writeToFile(filePath, toJsonString(objectToSerialize));
    }

    static <T> T deserializeObjectFromJsonFile(Path jsonFile, Class<T> classOfObjectToDeserialize)
            throws IOException {
        return fromJsonString(FileUtil.readFromFile(jsonFile), classOfObjectToDeserialize);
    }

    /**
     * Returns the Json object from the given file or {@code Optional.empty()} object if the file is not found.
     * If any values are missing from the file, default values will be used, as long as the file is a valid json file.
     *
     * @param filePath cannot be null.
     * @param classOfObjectToDeserialize Json file has to correspond to the structure in the class given here.
     * @throws DataConversionException if the file format is not as expected.
     */
    public static <T> Optional<T> readJsonFile(
            Path filePath, Class<T> classOfObjectToDeserialize) throws DataConversionException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("Json file " + filePath + " not found");
            return Optional.empty();
        }

        T jsonFile;

        try {
            jsonFile = deserializeObjectFromJsonFile(filePath, classOfObjectToDeserialize);
        } catch (IOException e) {
            logger.warning("Error reading from jsonFile file " + filePath + ": " + e);
            throw new DataConversionException(e);
        }

        return Optional.of(jsonFile);
    }

    /**
     * Saves the Json object to the specified file.
     * Overwrites existing file if it exists, creates a new file if it doesn't.
     *
     * @param jsonFile cannot be null
     * @param filePath cannot be null
     * @throws IOException if there was an error during writing to the file
     */
    public static <T> void saveJsonFile(T jsonFile, Path filePath) throws IOException {
        requireNonNull(filePath);
        requireNonNull(jsonFile);

        serializeObjectToJsonFile(filePath, jsonFile);
    }


    /**
     * Converts a given string representation of a JSON data to instance of a class.
     *
     * @param <T> The generic type to create an instance of
     * @return The instance of T with the specified values in the JSON string
     */
    public static <T> T fromJsonString(String json, Class<T> instanceClass) throws IOException {
        return objectMapper.readValue(json, instanceClass);
    }

    /**
     * Converts a given instance of a class into its JSON data string representation.
     *
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

        /**
         * Constructor for _deserialize.
         *
         * @param value of deserialize.
         * @param ctxt context of deserialization.
         * @return a _deserialize object.
         */
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

        /**
         * Returns the handled type.
         *
         * @return Class of the handled type.
         */
        @Override
        public Class<Level> handledType() {
            return Level.class;
        }
    }

    /**
     * Contains methods that retrieve logging level from serialized string.
     */
    private static class ImageDeserializer extends FromStringDeserializer<Image> {

        protected ImageDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        protected Image _deserialize(String value, DeserializationContext ctxt) throws IOException {
            byte[] decodedBytes = Base64.getDecoder().decode(value);
            return new Image(new ByteArrayInputStream(decodedBytes));
        }
    }

    /**
     * Contains methods that retrieve logging level from serialized string.
     */
    private static class ImageSerializer extends JsonSerializer<Image> {
        @Override
        public void serialize(Image value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            BufferedImage bImage = SwingFXUtils.fromFXImage(value, null);
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", s);
            byte[] res = s.toByteArray();
            gen.writeString(Base64.getEncoder().encodeToString(res));
        }
    }

}
