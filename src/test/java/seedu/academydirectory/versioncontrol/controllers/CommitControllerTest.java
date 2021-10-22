package seedu.academydirectory.versioncontrol.controllers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT1;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT2;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT3;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT4;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT5;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.testutil.TypicalCommits;
import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.storage.CommitStorageManager;
import seedu.academydirectory.versioncontrol.storage.TreeStorageManager;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class CommitControllerTest {
    private static final Path DATA_DIR = Paths.get("src", "test",
            "data", "VersionControlTest", "CommitControllerTest");
    private static final Path TESTING_DIR = Paths.get("src", "test", "temp");
    private static final HashGenerator hashGenerator = new HashGenerator(HashMethod.SHA1);

    @Test
    public void createNewCommit() {
        if (TESTING_DIR.toFile().exists()) {
            assertTrue(TESTING_DIR.toFile().delete());
        }
        assertTrue(TESTING_DIR.toFile().mkdir());

        TreeStorageManager treeStorageManager = new TreeStorageManager(TESTING_DIR);
        CommitStorageManager commitStorageManager = new CommitStorageManager(TESTING_DIR, treeStorageManager);
        CommitController commitController = new CommitController(hashGenerator, commitStorageManager);

        // Null Tree and Null Commit
        String message = "Initial Commit";
        Supplier<Tree> nullTreeSupplier = () -> Tree.NULL;
        Supplier<Commit> nullCommitSupplier = () -> Commit.NULL;
        Commit currentCommit = assertDoesNotThrow(() -> commitController.createNewCommit(message,
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
        currentCommit = assertDoesNotThrow(() -> commitController.createNewCommit(message,
                treeSupplier,
                commitSupplier));

        assertEquals(System.getProperty("user.name"), currentCommit.getAuthor());
        assertEquals(currentCommit.getDate(), currentCommit.getDate());
        assertEquals(message, currentCommit.getMessage());
        assertEquals(commitSupplier, currentCommit.getParentSupplier());
        assertEquals(treeSupplier, currentCommit.getTreeSupplier());

        assertTrue(TESTING_DIR.toFile().delete());
    }

    @Test
    public void fetchCommitByHash() throws IOException {
        if (TESTING_DIR.toFile().exists()) {
            assertTrue(TESTING_DIR.toFile().delete());
        }
        assertTrue(TESTING_DIR.toFile().mkdir());

        TreeStorageManager treeStorageManager = new TreeStorageManager(DATA_DIR);
        CommitStorageManager commitStorageManager = new CommitStorageManager(DATA_DIR, treeStorageManager);
        CommitController commitController = new CommitController(hashGenerator, commitStorageManager);

        // Exact Hash used -> Commit fetched successfully
        String commitHash = "1d83638a25901e76c8e3882afca2347f8352cd06";
        Path filepath = DATA_DIR.resolve(Paths.get(commitHash));
        assertTrue(filepath.toFile().exists()); // Check if file exists first

        Commit actualCommit = commitController.fetchCommitByHash(commitHash);
        assertEquals(COMMIT1, actualCommit);

        // fiveCharHash used -> Commit fetched successfully
        commitHash = commitHash.substring(0, 5);
        actualCommit = commitController.fetchCommitByHash(commitHash);
        assertEquals(COMMIT1, actualCommit);

        // given hash not present in disk -> Commit.NULL returned
        commitHash = "Testing123";
        actualCommit = commitController.fetchCommitByHash(commitHash);
        assertEquals(Commit.NULL, actualCommit);

        // given corrupt commit file -> Commit.NULL returned
        commitHash = "corrupted";
        actualCommit = commitController.fetchCommitByHash(commitHash);
        assertEquals(Commit.NULL, actualCommit);

        assertTrue(TESTING_DIR.toFile().delete());
    }

    @Test
    public void retrieveCommitHistory() {
        TreeStorageManager treeStorageManager = new TreeStorageManager(TESTING_DIR);
        CommitStorageManager commitStorageManager = new CommitStorageManager(TESTING_DIR, treeStorageManager);
        CommitController commitController = new CommitController(hashGenerator, commitStorageManager);

        Commit rootCommit = TypicalCommits.COMMIT2;
        Commit childCommit = TypicalCommits.COMMIT3;

        // With no end point
        Commit[] actualCommitHistory = commitController.retrieveCommitHistory(childCommit).toArray(Commit[]::new);
        Commit[] expectedCommitHistory = new Commit[]{childCommit, rootCommit};
        assertArrayEquals(expectedCommitHistory, actualCommitHistory);

        // With endpoint
        actualCommitHistory = commitController.retrieveCommitHistory(childCommit, rootCommit).toArray(Commit[]::new);
        expectedCommitHistory = new Commit[]{childCommit};
        assertArrayEquals(expectedCommitHistory, actualCommitHistory);

        // Same Commit -> Empty array
        actualCommitHistory = commitController.retrieveCommitHistory(childCommit, childCommit).toArray(Commit[]::new);
        assertEquals(0, actualCommitHistory.length);

        actualCommitHistory = commitController.retrieveCommitHistory(rootCommit, rootCommit).toArray(Commit[]::new);
        assertEquals(0, actualCommitHistory.length);

        // Start at Commit.Null -> Empty array
        actualCommitHistory = commitController.retrieveCommitHistory(Commit.NULL, childCommit).toArray(Commit[]::new);
        assertEquals(0, actualCommitHistory.length);
    }

    @Test
    public void findLca() {
        TreeStorageManager treeStorageManager = new TreeStorageManager(TESTING_DIR);
        CommitStorageManager commitStorageManager = new CommitStorageManager(TESTING_DIR, treeStorageManager);
        CommitController commitController = new CommitController(hashGenerator, commitStorageManager);

        // same commits
        assertEquals(COMMIT2, commitController.findLca(COMMIT2, COMMIT2));

        // LCA to Null commit is Null Commit
        assertEquals(Commit.NULL, commitController.findLca(COMMIT5, Commit.NULL));
        assertEquals(Commit.NULL, commitController.findLca(Commit.NULL, COMMIT5));

        // start with same depth
        Commit leftChildCommit = TypicalCommits.COMMIT3;
        Commit rightChildCommit = COMMIT4;
        assertEquals(COMMIT2, commitController.findLca(leftChildCommit, rightChildCommit));

        // start with different depth
        rightChildCommit = TypicalCommits.COMMIT5;
        assertEquals(COMMIT2, commitController.findLca(leftChildCommit, rightChildCommit));

        leftChildCommit = TypicalCommits.COMMIT5;
        rightChildCommit = TypicalCommits.COMMIT3;
        assertEquals(COMMIT2, commitController.findLca(leftChildCommit, rightChildCommit));
    }

    @Test
    public void getHighestAncestor() {
        TreeStorageManager treeStorageManager = new TreeStorageManager(TESTING_DIR);
        CommitStorageManager commitStorageManager = new CommitStorageManager(TESTING_DIR, treeStorageManager);
        CommitController commitController = new CommitController(hashGenerator, commitStorageManager);

        // No ancestor in range -> Commit.NULL
        assertEquals(Commit.NULL, commitController.getHighestAncestor(COMMIT2, COMMIT2));

        // No nodes between start Commit and endExclusive -> return start Commit
        assertEquals(COMMIT3, commitController.getHighestAncestor(COMMIT3, COMMIT2));
        assertEquals(COMMIT4, commitController.getHighestAncestor(COMMIT4, COMMIT2));

        // Return highest ancestor
        assertEquals(COMMIT4, commitController.getHighestAncestor(COMMIT5, COMMIT2));
    }
}
