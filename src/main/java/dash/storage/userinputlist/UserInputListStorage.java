package dash.storage.userinputlist;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import dash.commons.exceptions.DataConversionException;
import dash.model.UserInputList;

public interface UserInputListStorage {

    /**
     * Returns the file path of the UserInputList data file.
     */
    Path getUserInputListFilePath();

    /**
     * Returns UserInputList data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<UserInputList> readUserInputList() throws DataConversionException, IOException;

    /**
     * @see #getUserInputListFilePath()
     */
    Optional<UserInputList> readUserInputList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link UserInputList} to the storage.
     *
     * @param userInputList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserInputList(UserInputList userInputList) throws IOException;

    /**
     * @see #saveUserInputList(UserInputList)
     */
    void saveUserInputList(UserInputList userInputList, Path filePath) throws IOException;
}
