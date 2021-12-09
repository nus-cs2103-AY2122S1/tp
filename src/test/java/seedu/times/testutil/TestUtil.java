package seedu.times.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.times.commons.core.index.Index;
import seedu.times.model.Model;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;

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
     * Returns the middle index of the person in the {@code model}'s person list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredStudentList().size() / 2);
    }

    /**
     * Returns the last index of the person in the {@code model}'s person list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredStudentList().size());
    }

    /**
     * Returns the Student in the {@code model}'s student list at {@code index} - 1.
     */
    public static Student getStudentOneBased(Model model, int index) {
        return model.getFilteredStudentList().get(index - 1);
    }

    /**
     * Returns the tuition class in the {@code model}'s student list at {@code index} - 1.
     */
    public static TuitionClass getClassOneBased(Model model, int index) {
        return model.getFilteredTuitionClassList().get(index - 1);
    }

    public static List<Index> getIndexList(int... indices) {
        List<Index> res = new ArrayList<>();
        Arrays.stream(indices).forEach(index -> res.add(Index.fromOneBased(index)));
        return res;
    }
}
