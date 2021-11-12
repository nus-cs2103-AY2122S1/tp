package seedu.anilist.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;

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
     * Returns the middle index of the anime in the {@code model}'s anime list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredAnimeList().size() / 2);
    }

    /**
     * Returns the last index of the anime in the {@code model}'s anime list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredAnimeList().size());
    }

    /**
     * Returns the anime in the {@code model}'s anime list at {@code index}.
     */
    public static Anime getAnime(Model model, Index index) {
        return model.getFilteredAnimeList().get(index.getZeroBased());
    }
}
