package seedu.programmer.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.commons.exceptions.DataConversionException;
import seedu.programmer.commons.exceptions.IllegalValueException;

/**
 * Converts a Java object instance to JSON and vice versa.
 */
public class JsonUtil {

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .registerModule(new SimpleModule("SimpleModule")
                    .addSerializer(Level.class, new ToStringSerializer())
                    .addDeserializer(Level.class, new LevelDeserializer(Level.class)));

    static <T> void serializeObjectToJsonFile(Path jsonFile, T objectToSerialize) throws IOException {
        FileUtil.writeToFile(jsonFile, toJsonString(objectToSerialize));
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
     * @throws DataConversionException If the file format is not as expected.
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
     * @param jsonFile Cannot be null.
     * @param filePath cannot be null.
     * @throws IOException if there was an error during writing to the file.
     */
    public static <T> void saveJsonFile(T jsonFile, Path filePath) throws IOException {
        requireNonNull(filePath);
        requireNonNull(jsonFile);

        serializeObjectToJsonFile(filePath, jsonFile);
    }


    /**
     * Converts a given string representation of a JSON data to instance of a class.
     *
     * @param <T> The generic type to create an instance of.
     * @return The instance of T with the specified values in the JSON string.
     */
    public static <T> T fromJsonString(String json, Class<T> instanceClass) throws IOException {
        return objectMapper.readValue(json, instanceClass);
    }

    /**
     * Converts a given instance of a class into its JSON data string representation.
     *
     * @param instance The T object to be converted into the JSON string.
     * @param <T> The generic type to create an instance of.
     * @return JSON data representation of the given class instance, in string.
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
         * Gets the logging level that matches loggingLevelString.
         *
         * @return The logging level and returns null if there are no matches.
         */
        private Level getLoggingLevel(String loggingLevelString) {
            return Level.parse(loggingLevelString);
        }

        @Override
        public Class<Level> handledType() {
            return Level.class;
        }
    }

    /**
     * Writes JSON data to a CSV file.
     *
     * @param jsonData JSONArray of data.
     * @param destinationFile File object to write to.
     */
    public static void writeJsonToCsv(JSONArray jsonData, File destinationFile) {
        // If there were no data, we should not even be trying to write anything
        if (jsonData.length() <= 0) {
            return;
        }

        try {
            String rawJsonDataString = CDL.toString(jsonData);

            // Store unmarked lab scores as 0 instead of -1 for ease of upload to grade book
            String parsedJsonData = rawJsonDataString.replace("-1", "0");
            FileUtils.writeStringToFile(destinationFile, parsedJsonData, Charset.defaultCharset());
            logger.info("The following data was written:\n" + parsedJsonData);
        } catch (IOException | JSONException e) {
            logger.severe("Unexpected error: " + e);
        }
    }

    /**
     * Retrieves students' JSON data stored in ProgrammerError.
     *
     * @param filePath Path of file to get JSON data from.
     * @return JSONArray of student's data.
     * @throws IllegalValueException If the filePath is invalid.
     */
    public static JSONArray getJsonData(String filePath) throws IllegalValueException {
        try {
            InputStream is = new FileInputStream(filePath);
            String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(jsonTxt);
            return json.getJSONArray("students");
        } catch (IOException | JSONException e) {
            logger.severe("Error with the file!");
            throw new IllegalValueException("File not found");
        }
    }
}
