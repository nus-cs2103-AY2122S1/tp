package seedu.academydirectory.versioncontrol.objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT1;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT2;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT3;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT4;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT5;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.testutil.CommitBuilder;
import seedu.academydirectory.testutil.TypicalCommits;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class CommitTest {
    @Test
    public void equals() {
        Commit commit1Copy = new CommitBuilder(COMMIT1).build();
        // same values -> returns true
        assertEquals(COMMIT1, commit1Copy);

        // same object -> returns true
        assertEquals(COMMIT1, COMMIT1);

        // null -> returns false
        assertNotEquals(COMMIT1, null);

        // same hash, all other attributes different -> returns true
        Commit editedCommitSameHash = new CommitBuilder(COMMIT1)
                .withAuthor(COMMIT1.getAuthor() + "Hi")
                .withDate(new Date())
                .withMessage(COMMIT1.getMessage() + "Hi")
                .withParentSupplier(() -> COMMIT1)
                .withTreeSupplier(() -> new Tree("testing", "1", "2"))
                .build();
        assertEquals(COMMIT1, editedCommitSameHash);

        // different hash, all other attributes same -> returns false
        String filename = "CommitParserTest";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        String newHash = assertDoesNotThrow(() -> new HashGenerator(HashMethod.SHA1).generateHashFromFile(filepath));

        Commit editedCommitDiffHash = new CommitBuilder(COMMIT1).withHash(newHash).build();
        assertNotEquals(COMMIT1, editedCommitDiffHash);

        // null commits -> returns false
        assertNotEquals(COMMIT1, Commit.NULL);

        // null commits and null commits -> return true
        assertEquals(Commit.NULL, Commit.NULL);
    }

    @Test
    public void getHistory() {
        Commit rootCommit = TypicalCommits.COMMIT2;
        Commit childCommit = TypicalCommits.COMMIT3;

        // With no end point
        Commit[] actualCommitHistory = childCommit.getHistory().toArray(Commit[]::new);
        Commit[] expectedCommitHistory = new Commit[]{childCommit, rootCommit};
        assertArrayEquals(expectedCommitHistory, actualCommitHistory);

        // With endpoint
        actualCommitHistory = childCommit.getHistory(rootCommit).toArray(Commit[]::new);
        expectedCommitHistory = new Commit[]{childCommit};
        assertArrayEquals(expectedCommitHistory, actualCommitHistory);

        // Same Commit -> Empty array
        actualCommitHistory = childCommit.getHistory(childCommit).toArray(Commit[]::new);
        assertEquals(0, actualCommitHistory.length);

        actualCommitHistory = rootCommit.getHistory(rootCommit).toArray(Commit[]::new);
        assertEquals(0, actualCommitHistory.length);

        // Start at Commit.Null -> Empty array
        actualCommitHistory = Commit.NULL.getHistory(childCommit).toArray(Commit[]::new);
        assertEquals(0, actualCommitHistory.length);
    }

    @Test
    public void findLca() {
        // same commits
        assertEquals(COMMIT2, COMMIT2.findLca(COMMIT2));

        // LCA to Null commit is Null Commit
        assertEquals(Commit.NULL, COMMIT5.findLca(Commit.NULL));
        assertEquals(Commit.NULL, Commit.NULL.findLca(COMMIT5));

        // start with same depth
        Commit leftChildCommit = TypicalCommits.COMMIT3;
        Commit rightChildCommit = COMMIT4;
        assertEquals(COMMIT2, leftChildCommit.findLca(rightChildCommit));

        // start with different depth
        rightChildCommit = TypicalCommits.COMMIT5;
        assertEquals(COMMIT2, leftChildCommit.findLca(rightChildCommit));

        leftChildCommit = TypicalCommits.COMMIT5;
        rightChildCommit = TypicalCommits.COMMIT3;
        assertEquals(COMMIT2, leftChildCommit.findLca(rightChildCommit));
    }

    @Test
    public void getHighestAncestor() {
        // No ancestor in range -> Commit.NULL
        assertEquals(Commit.NULL, COMMIT2.getHighestAncestor(COMMIT2));

        // No nodes between start Commit and endExclusive -> return start Commit
        assertEquals(COMMIT3, COMMIT3.getHighestAncestor(COMMIT2));
        assertEquals(COMMIT4, COMMIT4.getHighestAncestor(COMMIT2));

        // Return highest ancestor
        assertEquals(COMMIT4, COMMIT5.getHighestAncestor(COMMIT2));
    }
}
