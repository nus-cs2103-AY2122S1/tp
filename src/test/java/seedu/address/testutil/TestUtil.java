package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.data.Data;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the data in either the {@code model}'s member or event list
     * based on specification.
     *
     * @param model is the model to look in
     * @param data is the string to identify which list to look
     */
    public static Index getMidIndex(Model model, String data) {
        switch (data) {
        case "member":
            return Index.fromOneBased(model.getFilteredMemberList().size() / 2);
        case "event":
            return Index.fromOneBased(model.getFilteredEventList().size() / 2);
        default:
            return null;
        }
    }

    /**
     * Returns the last index of the data in either the {@code model}'s member or event list
     * as specified.
     */
    public static Index getLastIndex(Model model, String data) {
        switch (data) {
        case "member":
            return Index.fromOneBased(model.getFilteredMemberList().size());
        case "event":
            return Index.fromOneBased(model.getFilteredEventList().size());
        default:
            return null;
        }
    }

    /**
     * Returns the data in the {@code model}'s member or event list at {@code index}
     * as specified.
     */
    public static Data getData(Model model, Index index, String data) {
        switch (data) {
        case "member":
            return model.getFilteredMemberList().get(index.getZeroBased());
        case "event":
            return model.getFilteredEventList().get(index.getZeroBased());
        default:
            return null;
        }
    }
}
