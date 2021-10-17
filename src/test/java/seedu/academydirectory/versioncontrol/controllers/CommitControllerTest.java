package seedu.academydirectory.versioncontrol.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class CommitControllerTest {
    private static final Path TESTING_DIR = Paths.get("src", "test", "temp");
    private static final HashGenerator hashGenerator = new HashGenerator(HashMethod.SHA1);

    @Test
    public void generateCommit() {
        if (TESTING_DIR.toFile().exists()) {
            assertTrue(TESTING_DIR.toFile().delete());
        }
        assertTrue(TESTING_DIR.toFile().mkdir());

        CommitController commitController = new CommitController(hashGenerator, TESTING_DIR);

        // Null Tree and Null Commit
        String message = "Testing";
        Supplier<Tree> nullTreeSupplier = () -> Tree.NULL;
        Supplier<Commit> nullCommitSupplier = () -> Commit.NULL;
        Commit currentCommit = assertDoesNotThrow(() -> commitController.generate(message,
                nullTreeSupplier,
                nullCommitSupplier));

        assertEquals(System.getProperty("user.name"), currentCommit.getAuthor());
        assertEquals(currentCommit.getDate(), currentCommit.getDate());
        assertEquals(message, currentCommit.getMessage());
        assertEquals(nullCommitSupplier, currentCommit.getParentSupplier());
        assertEquals(nullTreeSupplier, currentCommit.getTreeSupplier());

        // Non-Null Tree and Non-Null Commit
        Supplier<Tree> treeSupplier = () -> new Tree("Test", "TEst", "TEST");
        Supplier<Commit> commitSupplier = () -> COMMIT1;
        currentCommit = assertDoesNotThrow(() -> commitController.generate(message,
                treeSupplier,
                commitSupplier));

        assertEquals(System.getProperty("user.name"), currentCommit.getAuthor());
        assertEquals(currentCommit.getDate(), currentCommit.getDate());
        assertEquals(message, currentCommit.getMessage());
        assertEquals(commitSupplier, currentCommit.getParentSupplier());
        assertEquals(treeSupplier, currentCommit.getTreeSupplier());

        assertTrue(TESTING_DIR.toFile().delete());
    }
}
